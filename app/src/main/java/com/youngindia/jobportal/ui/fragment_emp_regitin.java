package com.youngindia.jobportal.ui;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.youngindia.jobportal.R;

import com.youngindia.jobportal.database.SessionManager;
import com.youngindia.jobportal.ui.app.AppConfig;
import com.youngindia.jobportal.ui.app.AppController;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the

 * to handle interaction events.
 * Use the {@link fragment_emp_regitin#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_emp_regitin extends Fragment implements SeekBar.OnSeekBarChangeListener{    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Employee_RegistrationActivity employee_registrationActivity;
    private final String TAG = fragment_emp_regitin.class.getSimpleName();
    Button nxtbtn;
    boolean checked=false;
    Spinner spinner_industry,spinner_state,spinner_city;;
    EditText edt_other,edt_other_location,keyskill,locationother_city;
    private EditText HighestEducation, Specialization, University;
    private Spinner Location, Industry, PassedYear,locationvalue,industryvalue;
    private ProgressDialog pDialog;
    AppController mInstance;
    AppConfig mappconfig;
    SessionManager session;
    private TextView tvDisplayDate;
    private DatePicker dpResult;
    private Button btnChangeDate;
    fragmnet_emp_reg2 fragmnet_emp_reg2;
    fragment_qualification fragment_qualification;
    EditText spinnervalue,locationother,industryother,currentcompany,designation;
    String value,candidatestatus="fresher";
    private int year;
    private int month;
    private int day;
    LinearLayout linearLayoutfranchisename,linearLayoutexperienceonly,linearLayout_Other_city;
    RadioButton male_radiobtn,female_radiobtn,radioButton_fresher,radioButton_experience,radioButton_workfromhome;
    RadioGroup radioGroup_gender, radioGroup_user;
    TextInputLayout inputLayout;
    static final int DATE_DIALOG_ID = 999;
    final ArrayList<String> state_options=new ArrayList<String>();
    final ArrayList<String> city_options=new ArrayList<String>();
    final ArrayList<String> state_options1=new ArrayList<String>();

    SeekBar seekBar_experience, seekBar_expected_salry;
    TextView textViewProgress, textView_expectedSalry;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    AutoCompleteTextView autoTextView1,autoTextView2;

    public fragment_emp_regitin() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_emp_regitin.
     */
    // TODO: Rename and change types and number of parameters
    public fragment_emp_regitin newInstance(String param1, String param2) {
        fragment_emp_regitin fragment = new fragment_emp_regitin();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_fragment_emp_regitin, container, false);
        ////
        String[] salary = {"1Lakhs","1Lakhs 20Thousand ","2Lakhs","2Lakhs 40Thousand ","4Lakhs","3Lakhs 80Thousand ","4Lakhs 60Thousand ",
                "1Lakhs 70Thousand ","1Lakhs 90Thousand ","5Lakhs","9Lakhs 20Thousand ","8Lakhs","7Lakhs 50Thousand ","6Lakhs","10Lakhs"};

        autoTextView1 = (AutoCompleteTextView) rootview.findViewById(R.id.autocompletecurrentsalary);
        autoTextView2 = (AutoCompleteTextView) rootview.findViewById(R.id.autocompleteexpectedsalary);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.select_dialog_item, salary);

        inputLayout=(TextInputLayout) rootview.findViewById(R.id.input_layout);
        autoTextView1.setThreshold(1);
        autoTextView2.setThreshold(1);
        autoTextView1.setAdapter(arrayAdapter);
        autoTextView2.setAdapter(arrayAdapter);
        tvDisplayDate = (TextView)rootview.findViewById(R.id.tvDate);
        // dpResult = (DatePicker)rootview.findViewById(R.id.dpResult);
        keyskill=(EditText)rootview.findViewById(R.id.edt_keySkills);
        spinner_state = (Spinner) rootview.findViewById(R.id.spinner_location_sate);
        spinner_city = (Spinner) rootview.findViewById(R.id.spinner_city_location);
        linearLayout_Other_city = (LinearLayout) rootview.findViewById(R.id.linearlayout_location_city);
        locationother_city= (EditText) rootview.findViewById(R.id.otherlocality_city);
        //keyskill.addTextChangedListener(new MyTextWatcher(keyskill));
        //  locationvalue=(Spinner)rootview.findViewById(R.id.spinner_location);
        industryvalue=(Spinner)rootview.findViewById(R.id.spinner_Industry);
        // locationother=(EditText)rootview.findViewById(R.id.edt_other_location);
        industryother=(EditText)rootview.findViewById(R.id.edt_others);
        currentcompany=(EditText)rootview.findViewById(R.id.edt_curent_company);
        designation=(EditText)rootview.findViewById(R.id.edt_curent_company_role);
        tvDisplayDate = (TextView) rootview.findViewById(R.id.tvDate);
        dpResult = (DatePicker) rootview.findViewById(R.id.dpResult);
        spinnervalue=(EditText)rootview.findViewById(R.id.edit_experience);
        seekBar_experience= (SeekBar) rootview.findViewById(R.id.seekBar_Experience);
        value = spinnervalue.getText().toString();
//        seekBar_experience.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//            }
//
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress,
//                                          boolean fromUser) {
///*int progress1;
//                progress1 = progress;
//                int lower = Color.argb(0xFF,progress*0x10000, progress*0x100, progress)+0xff000000;
//                seekBar.setBackgroundColor(lower);*/
//
//
//            }
//        });

        spinnervalue.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(count==0)
                {
                    seekBar_experience.setProgress(0);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(count==0)
                {
                    seekBar_experience.setProgress(0);
                }

            }



            @Override
            public void afterTextChanged(Editable s) {
                try {
                    //Update Seekbar value after entering a number

                    if((Float.parseFloat(s.toString()) <=10.00))
                    {
                        int currentProgress = seekBar_experience.getProgress();
                        seekBar_experience.setProgress(0);
                        Rect bounds = seekBar_experience.getProgressDrawable().getBounds();
                        seekBar_experience.setProgressDrawable(getResources().getDrawable(R.drawable.progress_bar));
                        seekBar_experience.getProgressDrawable().setBounds(bounds);
                        int forgetpsw=Math.round(Float.parseFloat(s.toString()));
                        seekBar_experience.setProgress(forgetpsw);
                        //seekBar_experience.setProgress(Integer.parseInt(s.toString()));
                        // spinnervalue.setSelection(spinnervalue.getText().length());
                        // seekBar_experience.getProgressDrawable().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
                        //int maxProgress=50;
                        //spinnervalue.setText(String.valueOf(progress));
               /* progress = progress;
                int lower = Color.argb(0xFF,progress*0x10000, progress*0x100, progress)+0xff000000;
                color.setBackgroundColor(lower);*/
                        /*float[] hsvColor = {0, 1, 1};
// generate only hue component in range [0, 360),
// leaving saturation and brightness maximum possible
                        hsvColor[0] = 360f * progress / maxProgress;
                        seekBar_experience.setBackgroundColor(Color.HSVToColor(hsvColor));*/


                    }
                    else if((Float.parseFloat(s.toString()) <=25.00))
                    {

                        int currentProgress = seekBar_experience.getProgress();
                        seekBar_experience.setProgress(0);
                        Rect bounds = seekBar_experience.getProgressDrawable().getBounds();
                        seekBar_experience.setProgressDrawable(getResources().getDrawable(R.drawable.progrees_bar1));
                        seekBar_experience.getProgressDrawable().setBounds(bounds);
                        int forgetpsw=Math.round(Float.parseFloat(s.toString()));
                        seekBar_experience.setProgress(forgetpsw);
                        //seekBar_experience.setProgress(currentProgress);
                        //seekBar_experience.setProgressTintList(ColorStateList.valueOf(Color.RED));

                        //spinnervalue.setSelection(spinnervalue.getText().length());
                        //seekBar_experience.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                      /*  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            seekBar_experience.setProgressDrawable(getResources()
                                    .getDrawable(R.drawable.progress_bar,getContext().getTheme()));

                        } else {
                            seekBar_experience.setProgressDrawable(getResources()
                                    .getDrawable(R.drawable.progress_bar));

                        }*/

                    }
                    else if((Float.parseFloat(s.toString()) >=26.00))
                    {
                        int currentProgress = seekBar_experience.getProgress();
                        seekBar_experience.setProgress(0);
                        Rect bounds = seekBar_experience.getProgressDrawable().getBounds();
                        seekBar_experience.setProgressDrawable(getResources().getDrawable(R.drawable.progrees_bar2));
                        seekBar_experience.getProgressDrawable().setBounds(bounds);
                        int forgetpsw=Math.round(Float.parseFloat(s.toString()));
                        seekBar_experience.setProgress(forgetpsw);
                        // spinnervalue.setSelection(spinnervalue.getText().length());

                        //seekBar_experience.getProgressDrawable().setColorFilter(Color.BLUE, PorterDuff.Mode.MULTIPLY);
                    }


                }catch (Exception ex) {
                }
            }
        });

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

//        // set current date into textview
//        tvDisplayDate.setText(new StringBuilder()
//                // Month is 0 based, just add 1
//                .append(month + 1).append("-").append(day).append("-")
//                .append(year).append(" "));
//        btnChangeDate = (Button) rootview.findViewById(R.id.btnChangeDate);
//
//        btnChangeDate.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//                DatePickerDialog datepicker = new DatePickerDialog(getContext(), datePickerListener,
//                        year, month, day);
//
//                datepicker.show();
//            }
//
//        });
        // set current date into datepicker
//        dpResult.init(year, month, day, null);
        ////
//        HighestEducation = (EditText) rootview.findViewById(R.id.hightEducation_ED);
//        Specialization = (EditText) rootview.findViewById(R.id.specilization_ED);
//        University = (EditText) rootview.findViewById(R.id.university_ED);
//        PassedYear = (Spinner) rootview.findViewById(R.id.spinner_yearOFpassed);
        Location = (Spinner) rootview.findViewById(R.id.spinner_location);

        Industry = (Spinner) rootview.findViewById(R.id.spinner_Industry);

        pDialog = new ProgressDialog(getActivity());
        pDialog.setCancelable(false);
        mInstance = new AppController();
        mappconfig = new AppConfig();

        //edt_other_location = (EditText) rootview.findViewById(R.id.edt_other_location);
        radioGroup_user= (RadioGroup)rootview.findViewById(R.id.radio_btn_group);
        radioButton_fresher= (RadioButton)rootview.findViewById(R.id.radioButton_fresher);
        radioButton_experience= (RadioButton)rootview.findViewById(R.id.radioButton_experience);
        radioButton_workfromhome=(RadioButton)rootview.findViewById(R.id.radioButton_workfromhome);
        linearLayoutfranchisename=(LinearLayout)rootview.findViewById(R.id.linearlayout_experience) ;
        linearLayoutexperienceonly=(LinearLayout)rootview.findViewById(R.id.linearlayout_experienceonly);
        linearLayoutexperienceonly.setVisibility(View.GONE);
        radioGroup_user.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId)
                {
                    case R.id.radioButton_experience:
                        candidatestatus="experienced";
                        radioButton_experience.isChecked();
                        linearLayoutexperienceonly.setVisibility(View.VISIBLE);
                        linearLayoutfranchisename.setVisibility(View.VISIBLE);
                        break;
                    case R.id.radioButton_fresher:
                        candidatestatus="fresher";
                        radioButton_fresher.isChecked();
                        linearLayoutexperienceonly.setVisibility(View.GONE);
                        linearLayoutfranchisename.setVisibility(View.GONE );
                        break;
                    case R.id.radioButton_workfromhome:
                        candidatestatus="fresher";
                        radioButton_workfromhome.isChecked();
                        linearLayoutexperienceonly.setVisibility(View.GONE);
                        linearLayoutfranchisename.setVisibility(View.GONE );
                        break;
                }
            }
        });

        state_options1.add("Andhra Pradesh");
        state_options1.add("Assam");
        state_options1.add("Arunachal Pradesh");
        state_options1.add("Bihar");
        state_options1.add("Chhatisgarh");
        state_options1.add("Goa");
        state_options1.add("Gujarat");
        state_options1.add("Haryana");
        state_options1.add("Himachal Pradesh");
        state_options1.add("Jammu & Kashmir");
        state_options1.add("Jharkhand");
        state_options1.add("Karnataka");
        state_options1.add("Kerala");
        state_options1.add("Madhya Pradesh");
        state_options1.add("Maharashtra");
        state_options1.add("Manipur");
        state_options1.add("Meghalaya");
        state_options1.add("Mizoram");
        state_options1.add("Nagaland");
        state_options1.add("Odisha");
        state_options1.add("Punjab");
        state_options1.add("Rajasthan");
        state_options1.add("Sikkim");
        state_options1.add("Telangana");
        state_options1.add("Tamilnadu");
        state_options1.add("Tripura");
        state_options1.add("Uttar Pradesh");
        state_options1.add("Uttrakhand");
        state_options1.add("West Bengal");
        //state_options1.add("Union Territories");

        city_options.add("Bagalkot");
        city_options.add("Bangalore");
        city_options.add("Bangalore Rural");
        city_options.add("Belgaum");
        city_options.add("Bellary");
        city_options.add("Bijapur");
        city_options.add("Chamarajanagar");
        city_options.add("Chikkaballapura");
        city_options.add("Chikmagalur");
        city_options.add("Chitradurga");
        city_options.add("Dakshina Kannada");
        city_options.add("Davanagere");
        city_options.add("Dharwad");
        city_options.add("Gadag");
        city_options.add("Gulbarga");
        city_options.add("Hassan");
        city_options.add("Haveri");
        city_options.add("Kodagu");
        city_options.add("Kolar");
        city_options.add("Koppal");
        city_options.add("Mandya");
        city_options.add("Mysore");
        city_options.add("Raichur");
        city_options.add("Ramanagara");
        city_options.add("Shimoga");
        city_options.add("Tumkur");
        city_options.add("Udupi");
        city_options.add("Uttara Kannada");
        city_options.add("Yadgir");
        city_options.add("others");
        //you can also get a cursor and add Strings as options to city_options instead of what i have done
        city_options.add("Bokaro");
        city_options.add("Chatra");
        city_options.add("Deoghar");
        city_options.add("Dhanbad district");
        city_options.add("Dumka");
        city_options.add("East Singhbhum");
        city_options.add("Garhwa");
        city_options.add("Giridih");
        city_options.add("Godda");
        city_options.add("Gumla");
        city_options.add("Hazaribagh");
        city_options.add("Jamtara");
        city_options.add("Khunti");
        city_options.add("Koderma");
        city_options.add("Latehar");
        city_options.add("Lohardaga");
        city_options.add("Pakur");
        city_options.add("Palamu");
        city_options.add("Ramgarh");
        city_options.add("Ranchi");
        city_options.add("Sahebganj");
        city_options.add("Saraikela Kharsawan");
        city_options.add("Simdega");
        city_options.add("West Singhbhum");
        city_options.add("others");
        // you can also get a cursor and add Strings as options to city_options instead of what i have done
        city_options.add("Anantapur");
        city_options.add("Chittoor");
        city_options.add("East Godavari");
        city_options.add("Guntur");
        city_options.add("Kadapa");
        city_options.add("Krishna");
        city_options.add("Kurnool");
        city_options.add("Nellore");
        city_options.add("Prakasam");
        city_options.add("Srikakulam");
        city_options.add("Visakhapatnam");
        city_options.add("Vizianagaram");
        city_options.add("West Godavari");
        city_options.add("others");
        city_options.add("Anini");
        city_options.add("Anjaw");
        city_options.add("Changlang");
        city_options.add("East Kameng");
        city_options.add("East Siang");
        city_options.add("Jamin");
        city_options.add("Koloriang");
        city_options.add("Lohit");
        city_options.add("Longding");
        city_options.add("Lower Dibang Valley");
        city_options.add("Lower Subansiri");
        city_options.add("Namsai");
        city_options.add("Papum Pare");
        city_options.add("Tawang");
        city_options.add("Tirap");
        city_options.add("Upper Siang");
        city_options.add("Upper Subansiri");
        city_options.add("West Kameng");
        city_options.add("West Siang");
        city_options.add("others");
        city_options.add("Amingaon");
        city_options.add("Barpeta");
        city_options.add("Biswanath Chariali");
        city_options.add("Bongaigaon");
        city_options.add("Cachar");
        city_options.add("Darrang");
        city_options.add("Dhemaji");
        city_options.add("Dhubri");
        city_options.add("Dibrugarh");
        city_options.add("Dima Hasao");
        city_options.add("Goalpara");
        city_options.add("Golaghat");

        // Bihar City..
        city_options.add("Araria");
        city_options.add("Arwal");
        city_options.add("Aurangabad");
        city_options.add("Banka");
        city_options.add("Begusarai");
        city_options.add("Bhagalpur");
        city_options.add("Bhojpur");
        city_options.add("Buxar");
        city_options.add("Darbhanga");
        city_options.add("East Champaran");
        city_options.add("Gaya");
        city_options.add("Gopalganj");
        city_options.add("Jamui");
        city_options.add("Jehanabad");
        city_options.add("Kaimur");
        city_options.add("Katihar");
        city_options.add("Khagaria");
        city_options.add("Kishanganj");
        city_options.add("Lakhisarai");
        city_options.add("Madhepura");
        city_options.add("Madhubani");
        city_options.add("Munger");
        city_options.add("Muzaffarpur");
        city_options.add("Nalanda");
        city_options.add("Nawada");
        city_options.add("Patna");
        city_options.add("Purnia");
        city_options.add("Rohtas");
        city_options.add("Saharsa");
        city_options.add("Samastipur");
        city_options.add("Saran");
        city_options.add("Sheikhpura");
        city_options.add("Sheohar");
        city_options.add("Sitamarhi");
        city_options.add("Siwan");
        city_options.add("Supaul");
        city_options.add("Vaishali");
        city_options.add("West Champaran");
        // Chhatisgarh
        city_options.add("Balod");
        city_options.add("Baloda Bazar");
        city_options.add("Balrampur");
        city_options.add("Bastar");
        city_options.add("Bemetara");
        city_options.add("Bijapur");
        city_options.add("Bilaspur");
        city_options.add("Dantewada");
        city_options.add("Dhamtari");
        city_options.add("Durg");
        city_options.add("Gariaband");
        city_options.add("Janjgir-Champa");
        city_options.add("Jashpur");
        city_options.add("Kabirdham");
        city_options.add("Kanker");
        city_options.add("Kondagaon");
        city_options.add("Korba");
        city_options.add("Koriya");
        city_options.add("Mahasamund");
        city_options.add("Mungeli");
        city_options.add("Narayanpur");
        city_options.add("Raigarh");
        city_options.add("Raipur");
        city_options.add("Rajnandgaon");
        city_options.add("Sukma");
        city_options.add("Surajpur");
        city_options.add("Surguja");
        //Goa
        city_options.add("North Goa");
        city_options.add("South Goa");
        // Gujarat
        city_options.add("Ahmedabad");
        city_options.add("Amreli");
        city_options.add("Anand");
        city_options.add("Aravalli");
        city_options.add("Banaskantha");
        city_options.add("Bharuch");
        city_options.add("Bhavnagar");
        city_options.add("Botad");
        city_options.add("Chhota Udaipur");
        city_options.add("Dahod");
        city_options.add("Dang");
        city_options.add("Devbhoomi Dwarka");
        city_options.add("Gandhinagar");
        city_options.add("Gir Somnath");
        city_options.add("Jamnagar");
        city_options.add("Junagadh");
        city_options.add("Kheda");
        city_options.add("Kutch");
        city_options.add("Mahisagar");
        city_options.add("Mehsana");
        city_options.add("Morbi");
        city_options.add("Narmada");
        city_options.add("Navsari");
        city_options.add("Panchmahal");
        city_options.add("Patan");
        city_options.add("Porbandar");
        city_options.add("Rajkot");
        city_options.add("Sabarkantha");
        city_options.add("Surat");
        city_options.add("Surendranagar");
        city_options.add("Tapi");
        city_options.add("Vadodara");
        city_options.add("Valsad");
        //Haryana
        city_options.add("Ambala");
        city_options.add("Bhiwani");
        city_options.add("Faridabad");
        city_options.add("Fatehabad");
        city_options.add("Gurgaon");
        city_options.add("Hisar");
        city_options.add("Jhajjar");
        city_options.add("Jind");
        city_options.add("Kaithal");
        city_options.add("Karnal");
        city_options.add("Kurukshetra");
        city_options.add("Mahendragarh");
        city_options.add("Mewat");
        city_options.add("Palwal");
        city_options.add("Panchkula");
        city_options.add("Panipat");
        city_options.add("Rewari");
        city_options.add("Rohtak");
        city_options.add("Sirsa");
        city_options.add("Sonipat");
        city_options.add("Yamuna Nagar");
        //Himachal Pradesh

        city_options.add("Bilaspur");
        city_options.add("Chamba");
        city_options.add("Hamirpur");
        city_options.add("Kangra");
        city_options.add("Kinnaur");
        city_options.add("Kullu");
        city_options.add("Lahaul and Spiti");
        city_options.add("Mandi");
        city_options.add("Shimla");
        city_options.add("Sirmaur");
        city_options.add("Solan");
        city_options.add("Una");
        //Jammu & Kashmir
        city_options.add("Anantnag");
        city_options.add("Bandipora");
        city_options.add("Baramulla");
        city_options.add("Budgam");
        city_options.add("Doda District");
        city_options.add("Ganderbal");
        city_options.add("Jammu District");
        city_options.add("Kargil");
        city_options.add("Kathua District");
        city_options.add("Kishtwar District");
        city_options.add("Kulgam");
        city_options.add("Kupwara");
        city_options.add("Leh");
        city_options.add("Poonch District");
        city_options.add("Pulwama");
        city_options.add("Rajouri District");
        city_options.add("Ramban District");
        city_options.add("Reasi District");
        city_options.add("Samba District");
        city_options.add("Shopian");
        city_options.add("Srinagar");
        city_options.add("Udhampur District");
        //Kerala
        city_options.add("Alappuzha");
        city_options.add("Ernakulam");
        city_options.add("Idukki");
        city_options.add("Kannur");
        city_options.add("Kasargod");
        city_options.add("Kollam");
        city_options.add("Kottayam");
        city_options.add("Kozhikode");
        city_options.add("Malappuram");
        city_options.add("Palakkad");
        city_options.add("Pathanamthitta");
        city_options.add("Thiruvananthapuram");
        city_options.add("Thrissur");
        city_options.add("Wayanad");
        //Madhya Pradesh
        city_options.add("Agarmalwa");
        city_options.add("Alirajpur");
        city_options.add("Anuppur");
        city_options.add("Ashoknagar");
        city_options.add("Balaghat");
        city_options.add("Barwani");
        city_options.add("Golaghat");
        city_options.add("Golaghat");
        city_options.add("Golaghat");
        city_options.add("Golaghat");
        city_options.add("Golaghat");
        city_options.add("Golaghat");
        city_options.add("Golaghat");
        city_options.add("Golaghat");
        city_options.add("Golaghat");
        city_options.add("Golaghat");
        city_options.add("Golaghat");
        city_options.add("Golaghat");
        //Maharashtra
        city_options.add("Ahmednagar");
        city_options.add("Akola");
        city_options.add("Amravati");
        city_options.add("Aurangabad");
        city_options.add("Beed");
        city_options.add("Bhandara");
        city_options.add("Buldhana");
        city_options.add("Chandrapur");
        city_options.add("Dhule");
        city_options.add("Gadchiroli");
        city_options.add("Gondia");
        city_options.add("Hingoli");
        city_options.add("Jalgaon");
        city_options.add("Jalna");
        city_options.add("Kolhapur");
        city_options.add("Latur");
        city_options.add("Mumbai City");
        city_options.add("Mumbai Suburban");
        city_options.add("Nagpur");
        city_options.add("Nanded");
        city_options.add("Nandurbar");
        city_options.add("Nashik");
        city_options.add("Osmanabad");
        city_options.add("Palghar");
        city_options.add("Parbhani");
        city_options.add("Pune");
        city_options.add("Raigad");
        city_options.add("Ratnagiri");
        city_options.add("Sangli");
        city_options.add("Satara");
        city_options.add("Sindhudurg");
        city_options.add("Solapur");
        city_options.add("Thane");
        city_options.add("Wardha");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
        //Manipur
        city_options.add("Bishnupur");
        city_options.add("Chandel");
        city_options.add("Churachandpur");
        city_options.add("Imphal East");
        city_options.add("Imphal West");
        city_options.add("Senapati");
        city_options.add("Tamenglong");
        city_options.add("Thoubal");
        city_options.add("Ukhrul");
        //Meghalaya
        city_options.add("East Garo Hills");
        city_options.add("East Jaintia Hills");
        city_options.add("East Khasi Hills");
        city_options.add("North Garo Hills");
        city_options.add("Ri Bhoi");
        city_options.add("South Garo Hills");
        city_options.add("South West Garo Hills");
        city_options.add("South West Khasi Hills");
        city_options.add("West Garo Hills");
        city_options.add("West Jaintia Hills");
        city_options.add("West Khasi Hills");
        //Mizoram
        city_options.add("Aizawl");
        city_options.add("Kolasib");
        city_options.add("Lawngtlai");
        city_options.add("Lunglei");
        city_options.add("Mamit");
        city_options.add("Serchhip");
        city_options.add("Siaha");
        //Nagaland
        city_options.add("Dimapur");
        city_options.add("Kiphire");
        city_options.add("Kohima");
        city_options.add("Longleng");
        city_options.add("Mokokchung");
        city_options.add("Mon");
        city_options.add("Peren");
        city_options.add("Phek");
        city_options.add("Tuensang");
        city_options.add("Wokha");
        city_options.add("Zunheboto");
        // Odisha
        city_options.add("Anugul");
        city_options.add("Balangir");
        city_options.add("Balasore (Baleswar)");
        city_options.add("Bargarh (Baragarh)");
        city_options.add("Bhadrak");
        city_options.add("Bhubaneswar");
        city_options.add("Boudh (Baudh)");
        city_options.add("Cuttack");
        city_options.add("Debagarh (Deogarh)");
        city_options.add("Dhenkanal");
        city_options.add("Gajapati");
        //Punjab
        city_options.add("Amritsar");
        city_options.add("Barnala");
        city_options.add("Bathinda");
        city_options.add("Faridkot");
        city_options.add("Fatehgarh Sahib");
        city_options.add("Fazilka[3]");
        city_options.add("Firozpur");
        city_options.add("Gurdaspur");
        city_options.add("Hoshiarpur");
        city_options.add("Jalandhar");
        city_options.add("Kapurthala");
        city_options.add("Ludhiana");
        city_options.add("Mansa");
        city_options.add("Moga");
        city_options.add("Pathankot");
        city_options.add("Patiala");
        city_options.add("Rupnagar");
        city_options.add("Sahibzada Ajit Singh Nagar");
        city_options.add("Sangrur");
        city_options.add("Shahid Bhagat Singh Nagar");
        city_options.add("Sri Muktsar Sahib");
        city_options.add("Tarn Taran");
        //Rajasthan
        city_options.add("Ajmer");
        city_options.add("Alwar");
        city_options.add("Banswara");
        city_options.add("Baran");
        city_options.add("Barmer");
        city_options.add("Bharatpur");
        city_options.add("Bhilwara");
        city_options.add("Bikaner");
        city_options.add("Nagaur");
        city_options.add("Pali");
        city_options.add("Pratapgarh[2]");
        city_options.add("Rajsamand");
        city_options.add("Sawai Madhopur");
        city_options.add("Sikar");
        city_options.add("Sirohi");
        city_options.add("Sri Ganganagar");
        city_options.add("Tonk");
        city_options.add("Udaipur");
        //Sikkim
        city_options.add("East Sikkim");
        city_options.add("North Sikkim");
        city_options.add("South Sikkim");
        city_options.add("West Sikkim");
        //Telangana
        city_options.add("Adilabad");
        city_options.add("Hyderabad");
        city_options.add("Karimnagar");
        city_options.add("Khammam");
        city_options.add("Mahbubnagar");
        city_options.add("Medak");
        city_options.add("Nalgonda");
        city_options.add("Nizamabads");
        city_options.add("Ranga Reddy");
        city_options.add("Warangal");
        //Tamilnadu
        city_options.add("Ariyalur");
        city_options.add("Chennai");
        city_options.add("Erode");
        city_options.add("Golaghat");
        city_options.add("Golaghat");
        city_options.add("Golaghat");
        city_options.add("Golaghat");
        city_options.add("Golaghat");
        city_options.add("Golaghat");
        //Tripura
        city_options.add("Dhalai");
        city_options.add("North Tripura");
        city_options.add("South Tripura");
        city_options.add("West Tripura");
        //Uttar Pradesh
        city_options.add("Agra");
        city_options.add("Aligarh");
        city_options.add("Allahabad");
        city_options.add("Bijnor");
        city_options.add("Chitrakoot");
        city_options.add("Varanasi");
        //Uttrakhand
        city_options.add("Almora");
        city_options.add("Bageshwar");
        city_options.add("Chamoli");
        city_options.add("Champawat");
        city_options.add("Dehradun");
        city_options.add("Haridwar");
        city_options.add("Nainital");
        city_options.add("Pauri Garhwal");
        city_options.add("Pithoragarh");
        city_options.add("Rudraprayag");
        city_options.add("Tehri Garhwal");
        city_options.add("Udham Singh Naga");
        city_options.add("Uttarkashi");
        //West Bengal
        city_options.add("Bankura");
        city_options.add("Barddhaman");
        city_options.add("Birbhum");
        city_options.add("Dakshin Dinajpur");
        city_options.add("Darjiling");
        city_options.add("Haora");
        city_options.add("Hugli");
        city_options.add("Jalpaiguri");
        city_options.add("Koch Bihar");
        city_options.add("Kolkata");
        city_options.add("Maldah");
        city_options.add("Murshidabad");
        city_options.add("Nadia");
        city_options.add("North Twenty Four Parganas");
        city_options.add("Paschim Medinipur");
        city_options.add("Purba Medinipur");
        city_options.add("Puruliya");
        city_options.add("South Twenty Four Parganas");
        city_options.add("Uttar Dinajpur");
//        //Union Territories
//        city_options.add("Andaman and Nicobar Islands.");

//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
        ArrayAdapter<String> cityAdapter1 = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.spinnertext, state_options1);
        spinner_state.setAdapter(cityAdapter1);

//        ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.spinnertext,city_options);
//        locatlity.setAdapter(cityAdapter);

        ArrayAdapter<String> stateAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.spinnertext, city_options);
        spinner_city.setAdapter(stateAdapter);

        spinner_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String stateName1 = state_options1.get(position).toString();
                resetCity1(stateName1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String city_NAME = city_options.get(position).toString();
                //  resetCity(city_NAME);
                value = parent.getSelectedItem().toString();
                if (value.equals("others")) {
                    value = "true";
                    linearLayout_Other_city.setVisibility(View.GONE);
                    locationother_city.setVisibility(View.VISIBLE);
                } else {
                    value = "false";
                    linearLayout_Other_city.setVisibility(View.VISIBLE);
                    locationother_city.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });


//
//        Location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                switch (position) {
//                    case 10:
//                        edt_other_location.setVisibility(View.VISIBLE);
//                        break;
//                    default:
//                        edt_other_location.setVisibility(View.GONE);
//                }
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//            }
//        });


        spinner_industry = (Spinner) rootview.findViewById(R.id.spinner_Industry);

        edt_other = (EditText) rootview.findViewById(R.id.edt_others);
        //  spinner_industry.setSelection(11);
        // spinner_industry.setSelection((ArrayAdapter)spinner_industry.getAdapter()("Category 2"));

        spinner_industry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                {

                    switch (position) {
                        case 11:
                            edt_other.setVisibility(View.VISIBLE);
                            //Toast.makeText(getActivity().getApplicationContext(),"hi",Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            edt_other.setVisibility(View.GONE);

                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
       /* if(spinner_industry.getSelectedItem().equals("Others"))
        {
            edt_other.setVisibility(View.VISIBLE);
        }*/

        // seekBar_expected_salry = (SeekBar) rootview.findViewById(R.id.seekBar_ExpectedSalary);
        // textView_expectedSalry = (TextView) rootview.findViewById(R.id.textProgressSalry);

        seekBar_experience = (SeekBar) rootview.findViewById(R.id.seekBar_Experience);
        seekBar_experience.setClickable(false);
        seekBar_experience.setEnabled(false);
        //   seekBar_experience.setOnSeekBarChangeListener((SeekBar.OnSeekBarChangeListener) this);
//        seekBar_expected_salry.setOnSeekBarChangeListener((SeekBar.OnSeekBarChangeListener) this);
        textViewProgress = (TextView) rootview.findViewById(R.id.textProgress);

        nxtbtn = (Button) rootview.findViewById(R.id.btn);
        session = new SessionManager(getActivity().getApplicationContext());

        /*if(session.getflag()==true)
        {

            HashMap<String, String> user1=session.getstorekeydetails();
            String keyskill1 = user1.get(SessionManager.KEY_SKILL);
            //  String  locationprfer=user1.get(SessionManager.KEY_LOCATIONPREFER);
            String locationspinnerval=user1.get(SessionManager.KEY_LOCATIONSPINNER);
            String industyprefer=user1.get(SessionManager.KEY_INDUSTRYPREFER);
            String industyspinner=user1.get(SessionManager.KEY_INDUSRTYSPINNER);
            String locationother=user1.get(SessionManager.KEY_LOCATIONOTHER);
            String candidatesttus1=user1.get(SessionManager.KEY_CANDIDATESTATUS);
            String industryother=user1.get(SessionManager.KEY_INDUSTRYOTHER);
            String currentcom=user1.get(SessionManager.KEY_CURRENTCOM);
            String currentdes=user1.get(SessionManager.KEY_CURRENTDES);
            String currentauto=user1.get(SessionManager.KEY_CURRENTAUTO);
            String expectedauto=user1.get(SessionManager.KEY_EXPECTEDAUTO);
            keyskill.setText(keyskill1);
            currentcompany.setText(currentcom);
            spinner_state.setSelection(Integer.parseInt(locationspinnerval));
            spinner_industry.setSelection(Integer.parseInt(industyspinner));
            designation.setText(currentdes);
            autoTextView1.setText(currentauto);
            autoTextView2.setText(expectedauto);
            if(candidatesttus1.equals("fresher")){radioButton_fresher.setChecked(true);}
            else{radioButton_experience.setChecked(true);}

        }*/
        nxtbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyskills=keyskill.getText().toString().trim();
                //  String locationprefer=locationvalue.getSelectedItem().toString().trim();
                String industryprefer=industryvalue.getSelectedItem().toString().trim();
                String locationothers1=spinner_city.getSelectedItem().toString().trim();
                String locationothers=locationother_city.getText().toString().trim();
                String locationspinnervalue= spinner_city.getSelectedItem().toString().trim();
                String industryspinnervalue=industryvalue.getSelectedItem().toString().trim();
                String industryothers=industryother.getText().toString().trim();
                String currentcom=currentcompany.getText().toString().trim();
                String currentdes=designation.getText().toString().trim();
                String currentauto=autoTextView1.getText().toString().trim();
                String expectedauto=autoTextView2.getText().toString().trim();
                session = new SessionManager(getActivity().getApplicationContext());
                session.storekeydetails(keyskills,industryprefer,
                        locationothers,industryothers,currentcom,currentdes,currentauto,expectedauto,locationspinnervalue,industryspinnervalue,candidatestatus);


//                String highEducation = HighestEducation.getText().toString().trim();
//                String specialization = Specialization.getText().toString().trim();
//                String university = University.getText().toString().trim();
//                String passedYear = PassedYear.getSelectedItem().toString().trim();
//                String location=Location.getSelectedItem().toString().trim();
//                String industry=Industry.getSelectedItem().toString().trim();
//                session = new SessionManager(getActivity().getApplicationContext());
//                HashMap<String, String> user1=session.getUsername();
//                String username = user1.get(SessionManager.KEY_NAME);
              /*  if (!highEducation.isEmpty() && !specialization.isEmpty() && !university.isEmpty() && !passedYear.isEmpty()
                        && !location.isEmpty() && !industry.isEmpty() && !username.isEmpty()) {

                    registerUser(highEducation, specialization, university, passedYear,location,industry,username);
                } else {
                    Toast.makeText(getActivity().getApplicationContext(),
                            "Please enter your details!", Toast.LENGTH_LONG)
                            .show();

                }*/



//                String highEducation = HighestEducation.getText().toString().trim();
//                String specialization = Specialization.getText().toString().trim();
//                String university = University.getText().toString().trim();
//                String passedYear = PassedYear.getSelectedItem().toString().trim();
//                String location = Location.getSelectedItem().toString().trim();
//                String industry = Industry.getSelectedItem().toString().trim();
//                session = new SessionManager(getActivity().getApplicationContext());
//                HashMap<String, String> user1 = session.getUsername();
//                String username = user1.get(SessionManager.KEY_NAME);
//                if (!highEducation.isEmpty() && !specialization.isEmpty() && !university.isEmpty() && !passedYear.isEmpty()
//                        && !location.isEmpty() && !industry.isEmpty() && !username.isEmpty()) {
//
//                    registerUser(highEducation, specialization, university, passedYear, location, industry, username);
//                } else {
//                    Toast.makeText(getActivity().getApplicationContext(),
//                            "Please enter your details!", Toast.LENGTH_LONG)
//                            .show();
//                }
                fragmnet_emp_reg2 = new fragmnet_emp_reg2();
                fragment_qualification=new fragment_qualification();

                FragmentTransaction fragmenttransaction = employee_registrationActivity.getSupportFragmentManager().beginTransaction();
                //   sScreen = getResources().getString(R.string.title_wall);
                fragmenttransaction.replace(R.id.frame_container1, fragment_qualification);
                fragmenttransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragmenttransaction.addToBackStack("tag0").commit();

            }
        });
        // Inflate the layout for this fragment
        return rootview;

    }


    private void registerUser(final String highEducation, final String specialization,
                              final String university, final String passedyear, final String location, final String industry, final String username) {
        // Tag used to cancel the request
        String tag_string_req = "req_register";

        pDialog.setMessage("Registering ...");

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_USERDETAIL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Register Response: " + response.toString());

                try {
                    JSONObject jObj = null;
                    try {
                        jObj = new JSONObject(response);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    boolean error = jObj.getBoolean("error");
                    if (!error) {

                        fragmnet_emp_reg2 fragmnet_emp_reg2 = new fragmnet_emp_reg2();

                        FragmentTransaction fragmenttransaction = employee_registrationActivity.getSupportFragmentManager().beginTransaction();
                        //   sScreen = getResources().getString(R.string.title_wall);
                        fragmenttransaction.replace(R.id.frame_container1, fragmnet_emp_reg2);
                        fragmenttransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        fragmenttransaction.addToBackStack("tag").commit();
                        Toast.makeText(getActivity().getApplicationContext(), "User details successfully stored", Toast.LENGTH_LONG).show();

                    } else {

                        // Error occurred in registration. Get the error
                        // message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getActivity().getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Registration Error: " + error.getMessage());
                Toast.makeText(getActivity().getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                // hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", username);
                params.put("highEducation", highEducation);
                params.put("specialization", specialization);
                params.put("university", university);
                params.put("passedyear", passedyear);
                params.put("location", location);
                params.put("industry", industry);


                return params;
            }

        };

        // Adding request to request queue
        mInstance.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        employee_registrationActivity = (Employee_RegistrationActivity) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
       /* if (seekBar == seekBar_experience) {
            textViewProgress.setText("Your Experience is :  " + progress);
        } else {
            textView_expectedSalry.setText("Your Expected Salary is:" + progress);
        }*/

    }

    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    public void onStopTrackingTouch(SeekBar seekBar) {
        seekBar.setSecondaryProgress(seekBar.getProgress());

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    // display current date
    public void setCurrentDateOnView() {


    }


    private DatePickerDialog.OnDateSetListener datePickerListener
            = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;

            // set selected date into textview
            tvDisplayDate.setText(new StringBuilder().append(month + 1)
                    .append("-").append(day).append("-").append(year)
                    .append(" "));

        }
    };

    @Override
    public void onResume() {
        super.onResume();
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.setTitle("Professional Details");
        employee_registrationActivity.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getActivity(),LoginUser_Activity.class);
                startActivity(intent);
            }
        });
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;

        }


        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.edt_keySkills:
                    validateEmail();
                    break;
            }
        }

    }
    public  boolean validateEmail1() {
        String email_id = keyskill.getText().toString().trim();

        if ( email_id.isEmpty()) {
            inputLayout.setError("*");
            inputLayout.setErrorEnabled(true);
            return false;
        } else {
            inputLayout.setError(" ");
            inputLayout.setErrorEnabled(false);
            return true;
        }

    }


    private boolean validateEmail() {
        String email_id = keyskill.getText().toString().trim();

        if ( email_id.isEmpty()) {
            inputLayout.setError("Please fillup this field!!!!");
            keyskill.requestFocus();
            return false;
        } else {
            inputLayout.setError(" ");
            inputLayout.setErrorEnabled(false);
            return true;
        }

    }
    public void resetCity1(String stateName)
    {
        city_options.removeAll(city_options);//i haven't checked this.
        if(stateName.equals("Andhra Pradesh"))
        {
            city_options.add("Anantapur");
            city_options.add("Chittoor");
            city_options.add("East Godavari");
            city_options.add("Guntur");
            city_options.add("Kadapa");
            city_options.add("Krishna");
            city_options.add("Kurnool");
            city_options.add("Nellore");
            city_options.add("Prakasam");
            city_options.add("Srikakulam");
            city_options.add("Visakhapatnam");
            city_options.add("Vizianagaram");
            city_options.add("West Godavari");
            city_options.add("others");
        }
        else if (stateName.equals("Arunachal Pradesh"))
        {
            city_options.add("Anini");
            city_options.add("Anjaw");
            city_options.add("Changlang");
            city_options.add("East Kameng");
            city_options.add("East Siang");
            city_options.add("Jamin");
            city_options.add("Koloriang");
            city_options.add("Lohit");
            city_options.add("Longding");
            city_options.add("Lower Dibang Valley");
            city_options.add("Lower Subansiri");
            city_options.add("Namsai");
            city_options.add("Papum Pare");
            city_options.add("Tawang");
            city_options.add("Tirap");
            city_options.add("Upper Siang");
            city_options.add("Upper Subansiri");
            city_options.add("West Kameng");
            city_options.add("West Siang");
            city_options.add("others");
        }
        else if (stateName.equals("Assam"))
        {
            city_options.add("Amingaon");
            city_options.add("Barpeta");
            city_options.add("Biswanath Chariali");
            city_options.add("Bongaigaon");
            city_options.add("Cachar");
            city_options.add("Darrang");
            city_options.add("Dhemaji");
            city_options.add("Dhubri");
            city_options.add("Dibrugarh");
            city_options.add("Dima Hasao");
            city_options.add("Goalpara");
            city_options.add("Golaghat");
            city_options.add("others");

        }
        else if(stateName.equals("Bihar"))
        {
            city_options.add("Araria");
            city_options.add("Arwal");
            city_options.add("Aurangabad");
            city_options.add("Banka");
            city_options.add("Begusarai");
            city_options.add("Bhagalpur");
            city_options.add("Bhojpur");
            city_options.add("Buxar");
            city_options.add("Darbhanga");
            city_options.add("East Champaran");
            city_options.add("Gaya");
            city_options.add("Gopalganj");
            city_options.add("Jamui");
            city_options.add("Jehanabad");
            city_options.add("Kaimur");
            city_options.add("Katihar");
            city_options.add("Khagaria");
            city_options.add("Kishanganj");
            city_options.add("Lakhisarai");
            city_options.add("Madhepura");
            city_options.add("Madhubani");
            city_options.add("Munger");
            city_options.add("Muzaffarpur");
            city_options.add("Nalanda");
            city_options.add("Nawada");
            city_options.add("Patna");
            city_options.add("Purnia");
            city_options.add("Rohtas");
            city_options.add("Saharsa");
            city_options.add("Samastipur");
            city_options.add("Saran");
            city_options.add("Sheikhpura");
            city_options.add("Sheohar");
            city_options.add("Sitamarhi");
            city_options.add("Siwan");
            city_options.add("Supaul");
            city_options.add("Vaishali");
            city_options.add("West Champaran");
            city_options.add("others");
        }
        else if(stateName.equals("Chhatisgarh"))
        {
            city_options.add("Balod");
            city_options.add("Baloda Bazar");
            city_options.add("Balrampur");
            city_options.add("Bastar");
            city_options.add("Bemetara");
            city_options.add("Bijapur");
            city_options.add("Bilaspur");
            city_options.add("Dantewada");
            city_options.add("Dhamtari");
            city_options.add("Durg");
            city_options.add("Gariaband");
            city_options.add("Janjgir-Champa");
            city_options.add("Jashpur");
            city_options.add("Kabirdham");
            city_options.add("Kanker");
            city_options.add("Kondagaon");
            city_options.add("Korba");
            city_options.add("Koriya");
            city_options.add("Mahasamund");
            city_options.add("Mungeli");
            city_options.add("Narayanpur");
            city_options.add("Raigarh");
            city_options.add("Raipur");
            city_options.add("Rajnandgaon");
            city_options.add("Sukma");
            city_options.add("Surajpur");
            city_options.add("Surguja");
            city_options.add("others");
        }
        else if(stateName.equals("Goa"))
        {
            //Goa
            city_options.add("North Goa");
            city_options.add("South Goa");
            city_options.add("others");
        }
        else if(stateName.equals("Gujarat"))
        {
            // Gujarat
            city_options.add("Ahmedabad");
            city_options.add("Amreli");
            city_options.add("Anand");
            city_options.add("Aravalli");
            city_options.add("Banaskantha");
            city_options.add("Bharuch");
            city_options.add("Bhavnagar");
            city_options.add("Botad");
            city_options.add("Chhota Udaipur");
            city_options.add("Dahod");
            city_options.add("Dang");
            city_options.add("Devbhoomi Dwarka");
            city_options.add("Gandhinagar");
            city_options.add("Gir Somnath");
            city_options.add("Jamnagar");
            city_options.add("Junagadh");
            city_options.add("Kheda");
            city_options.add("Kutch");
            city_options.add("Mahisagar");
            city_options.add("Mehsana");
            city_options.add("Morbi");
            city_options.add("Narmada");
            city_options.add("Navsari");
            city_options.add("Panchmahal");
            city_options.add("Patan");
            city_options.add("Porbandar");
            city_options.add("Rajkot");
            city_options.add("Sabarkantha");
            city_options.add("Surat");
            city_options.add("Surendranagar");
            city_options.add("Tapi");
            city_options.add("Vadodara");
            city_options.add("Valsad");
            city_options.add("others");
        }
        else if(stateName.equals("Haryana"))
        {            //Haryana
            city_options.add("Ambala");
            city_options.add("Bhiwani");
            city_options.add("Faridabad");
            city_options.add("Fatehabad");
            city_options.add("Gurgaon");
            city_options.add("Hisar");
            city_options.add("Jhajjar");
            city_options.add("Jind");
            city_options.add("Kaithal");
            city_options.add("Karnal");
            city_options.add("Kurukshetra");
            city_options.add("Mahendragarh");
            city_options.add("Mewat");
            city_options.add("Palwal");
            city_options.add("Panchkula");
            city_options.add("Panipat");
            city_options.add("Rewari");
            city_options.add("Rohtak");
            city_options.add("Sirsa");
            city_options.add("Sonipat");
            city_options.add("Yamuna Nagar");
            city_options.add("others");

        }
        else if(stateName.equals("Himachal Pradesh")) {
            //Himachal Pradesh

            city_options.add("Bilaspur");
            city_options.add("Chamba");
            city_options.add("Hamirpur");
            city_options.add("Kangra");
            city_options.add("Kinnaur");
            city_options.add("Kullu");
            city_options.add("Lahaul and Spiti");
            city_options.add("Mandi");
            city_options.add("Shimla");
            city_options.add("Sirmaur");
            city_options.add("Solan");
            city_options.add("Una");
            city_options.add("others");
        }
        else if(stateName.equals("Jammu & Kashmir")) {
            //Jammu & Kashmir
            city_options.add("Anantnag");
            city_options.add("Bandipora");
            city_options.add("Baramulla");
            city_options.add("Budgam");
            city_options.add("Doda District");
            city_options.add("Ganderbal");
            city_options.add("Jammu District");
            city_options.add("Kargil");
            city_options.add("Kathua District");
            city_options.add("Kishtwar District");
            city_options.add("Kulgam");
            city_options.add("Kupwara");
            city_options.add("Leh");
            city_options.add("Poonch District");
            city_options.add("Pulwama");
            city_options.add("Rajouri District");
            city_options.add("Ramban District");
            city_options.add("Reasi District");
            city_options.add("Samba District");
            city_options.add("Shopian");
            city_options.add("Srinagar");
            city_options.add("Udhampur District");
            city_options.add("others");
        }
        else if (stateName.equals("Jharkhand"))
        {
            city_options.add("Bokaro");
            city_options.add("Chatra");
            city_options.add("Deoghar");
            city_options.add("Dhanbad district");
            city_options.add("Dumka");
            city_options.add("East Singhbhum");
            city_options.add("Garhwa");
            city_options.add("Giridih");
            city_options.add("Godda");
            city_options.add("Gumla");
            city_options.add("Hazaribagh");
            city_options.add("Jamtara");
            city_options.add("Khunti");
            city_options.add("Koderma");
            city_options.add("Latehar");
            city_options.add("Lohardaga");
            city_options.add("Pakur");
            city_options.add("Palamu");
            city_options.add("Ramgarh");
            city_options.add("Ranchi");
            city_options.add("Sahebganj");
            city_options.add("Saraikela Kharsawan");
            city_options.add("Simdega");
            city_options.add("West Singhbhum");
            city_options.add("others");
            // you can also get a cursor and add Strings as options to city_options instead of what i have done
        }
        else if (stateName.equals("Karnataka"))
        {
            city_options.add("Bagalkot");
            city_options.add("Bangalore");
            city_options.add("Bangalore Rural");
            city_options.add("Belgaum");
            city_options.add("Bellary");
            city_options.add("Bijapur");
            city_options.add("Chamarajanagar");
            city_options.add("Chikkaballapura");
            city_options.add("Chikmagalur");
            city_options.add("Chitradurga");
            city_options.add("Dakshina Kannada");
            city_options.add("Davanagere");
            city_options.add("Dharwad");
            city_options.add("Gadag");
            city_options.add("Gulbarga");
            city_options.add("Hassan");
            city_options.add("Haveri");
            city_options.add("Kodagu");
            city_options.add("Kolar");
            city_options.add("Koppal");
            city_options.add("Mandya");
            city_options.add("Mysore");
            city_options.add("Raichur");
            city_options.add("Ramanagara");
            city_options.add("Shimoga");
            city_options.add("Tumkur");
            city_options.add("Udupi");
            city_options.add("Uttara Kannada");
            city_options.add("Yadgir");
            city_options.add("others");
            //you can also get a cursor and add Strings as options to city_options instead of what i have done
        }
        else if(stateName.equals("Kerala"))
        {
            //Kerala
            city_options.add("Alappuzha");
            city_options.add("Ernakulam");
            city_options.add("Idukki");
            city_options.add("Kannur");
            city_options.add("Kasargod");
            city_options.add("Kollam");
            city_options.add("Kottayam");
            city_options.add("Kozhikode");
            city_options.add("Malappuram");
            city_options.add("Palakkad");
            city_options.add("Pathanamthitta");
            city_options.add("Thiruvananthapuram");
            city_options.add("Thrissur");
            city_options.add("Wayanad");     city_options.add("others");
        }
        else if(stateName.equals("Madhya Pradesh")) {
            //Madhya Pradesh
            city_options.add("Agarmalwa");
            city_options.add("Alirajpur");
            city_options.add("Anuppur");
            city_options.add("Ashoknagar");
            city_options.add("Balaghat");
            city_options.add("Barwani");
            city_options.add("others");
//            city_options.add("Golaghat");
//            city_options.add("Golaghat");
//            city_options.add("Golaghat");
//            city_options.add("Golaghat");
//            city_options.add("Golaghat");
//            city_options.add("Golaghat");
//            city_options.add("Golaghat");
//            city_options.add("Golaghat");
//            city_options.add("Golaghat");
//            city_options.add("Golaghat");
//            city_options.add("Golaghat");
//            city_options.add("Golaghat");
            city_options.add("others");
        }
        else if(stateName.equals("Maharashtra")) {
            //Maharashtra
            city_options.add("Ahmednagar");
            city_options.add("Akola");
            city_options.add("Amravati");
            city_options.add("Aurangabad");
            city_options.add("Beed");
            city_options.add("Bhandara");
            city_options.add("Buldhana");
            city_options.add("Chandrapur");
            city_options.add("Dhule");
            city_options.add("Gadchiroli");
            city_options.add("Gondia");
            city_options.add("Hingoli");
            city_options.add("Jalgaon");
            city_options.add("Jalna");
            city_options.add("Kolhapur");
            city_options.add("Latur");
            city_options.add("Mumbai City");
            city_options.add("Mumbai Suburban");
            city_options.add("Nagpur");
            city_options.add("Nanded");
            city_options.add("Nandurbar");
            city_options.add("Nashik");
            city_options.add("Osmanabad");
            city_options.add("Palghar");
            city_options.add("Parbhani");
            city_options.add("Pune");
            city_options.add("Raigad");
            city_options.add("Ratnagiri");
            city_options.add("Sangli");
            city_options.add("Satara");
            city_options.add("Sindhudurg");
            city_options.add("Solapur");
            city_options.add("Thane");
            city_options.add("Wardha");
            city_options.add("others");
        }
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
//        city_options.add("Golaghat");
        else if (stateName.equals("Manipur")) {
            //Manipur
            city_options.add("Bishnupur");
            city_options.add("Chandel");
            city_options.add("Churachandpur");
            city_options.add("Imphal East");
            city_options.add("Imphal West");
            city_options.add("Senapati");
            city_options.add("Tamenglong");
            city_options.add("Thoubal");
            city_options.add("Ukhrul");     city_options.add("others");
        }
        else if(stateName.equals("Meghalaya")) {
            //Meghalaya
            city_options.add("East Garo Hills");
            city_options.add("East Jaintia Hills");
            city_options.add("East Khasi Hills");
            city_options.add("North Garo Hills");
            city_options.add("Ri Bhoi");
            city_options.add("South Garo Hills");
            city_options.add("South West Garo Hills");
            city_options.add("South West Khasi Hills");
            city_options.add("West Garo Hills");
            city_options.add("West Jaintia Hills");
            city_options.add("West Khasi Hills");     city_options.add("others");
        }
        else if(stateName.equals("Mizoram")) {
            //Mizoram
            city_options.add("Aizawl");
            city_options.add("Kolasib");
            city_options.add("Lawngtlai");
            city_options.add("Lunglei");
            city_options.add("Mamit");
            city_options.add("Serchhip");
            city_options.add("Siaha");     city_options.add("others");
        }
        else if(stateName.equals("Nagaland")) {
            //Nagaland
            city_options.add("Dimapur");
            city_options.add("Kiphire");
            city_options.add("Kohima");
            city_options.add("Longleng");
            city_options.add("Mokokchung");
            city_options.add("Mon");
            city_options.add("Peren");
            city_options.add("Phek");
            city_options.add("Tuensang");
            city_options.add("Wokha");
            city_options.add("Zunheboto");     city_options.add("others");
        }
        else if(stateName.equals("Odisha")) {
            // Odisha
            city_options.add("Anugul");
            city_options.add("Balangir");
            city_options.add("Balasore (Baleswar)");
            city_options.add("Bargarh (Baragarh)");
            city_options.add("Bhadrak");
            city_options.add("Bhubaneswar");
            city_options.add("Boudh (Baudh)");
            city_options.add("Cuttack");
            city_options.add("Debagarh (Deogarh)");
            city_options.add("Dhenkanal");
            city_options.add("Gajapati");     city_options.add("others");
        }
        else if(stateName.equals("Punjab")) {
            //Punjab
            city_options.add("Amritsar");
            city_options.add("Barnala");
            city_options.add("Bathinda");
            city_options.add("Faridkot");
            city_options.add("Fatehgarh Sahib");
            city_options.add("Fazilka[3]");
            city_options.add("Firozpur");
            city_options.add("Gurdaspur");
            city_options.add("Hoshiarpur");
            city_options.add("Jalandhar");
            city_options.add("Kapurthala");
            city_options.add("Ludhiana");
            city_options.add("Mansa");
            city_options.add("Moga");
            city_options.add("Pathankot");
            city_options.add("Patiala");
            city_options.add("Rupnagar");
            city_options.add("Sahibzada Ajit Singh Nagar");
            city_options.add("Sangrur");
            city_options.add("Shahid Bhagat Singh Nagar");
            city_options.add("Sri Muktsar Sahib");
            city_options.add("Tarn Taran");     city_options.add("others");
        }
        else if(stateName.equals("Rajasthan")) {
            //Rajasthan
            city_options.add("Ajmer");
            city_options.add("Alwar");
            city_options.add("Banswara");
            city_options.add("Baran");
            city_options.add("Barmer");
            city_options.add("Bharatpur");
            city_options.add("Bhilwara");
            city_options.add("Bikaner");
            city_options.add("Nagaur");
            city_options.add("Pali");
            city_options.add("Pratapgarh[2]");
            city_options.add("Rajsamand");
            city_options.add("Sawai Madhopur");
            city_options.add("Sikar");
            city_options.add("Sirohi");
            city_options.add("Sri Ganganagar");
            city_options.add("Tonk");
            city_options.add("Udaipur");     city_options.add("others");
        }
        else if(stateName.equals("Sikkim")) {
            //Sikkim
            city_options.add("East Sikkim");
            city_options.add("North Sikkim");
            city_options.add("South Sikkim");
            city_options.add("West Sikkim");
        }
        else if(stateName.equals("Telangana")) {
            //Telangana
            city_options.add("Adilabad");
            city_options.add("Hyderabad");
            city_options.add("Karimnagar");
            city_options.add("Khammam");
            city_options.add("Mahbubnagar");
            city_options.add("Medak");
            city_options.add("Nalgonda");
            city_options.add("Nizamabads");
            city_options.add("Ranga Reddy");
            city_options.add("Warangal");     city_options.add("others");
        }
        else if(stateName.equals("Tamilnadu")) {
            //Tamilnadu
            city_options.add("Ariyalur");
            city_options.add("Chennai");
            city_options.add("Erode");
            city_options.add("Golaghat");
            city_options.add("Golaghat");
            city_options.add("Golaghat");
            city_options.add("Golaghat");
            city_options.add("Golaghat");
            city_options.add("Golaghat");     city_options.add("others");
        }
        else if(stateName.equals("Tripura")) {
            //Tripura
            city_options.add("Dhalai");
            city_options.add("North Tripura");
            city_options.add("South Tripura");
            city_options.add("West Tripura");     city_options.add("others");
        }
        else if(stateName.equals("Uttar Pradesh")) {
            //Uttar Pradesh
            city_options.add("Agra");
            city_options.add("Aligarh");
            city_options.add("Allahabad");
            city_options.add("Bijnor");
            city_options.add("Chitrakoot");
            city_options.add("Varanasi");     city_options.add("others");
        }
        else if(stateName.equals("Uttrakhand")) {
            //Uttrakhand
            city_options.add("Almora");
            city_options.add("Bageshwar");
            city_options.add("Chamoli");
            city_options.add("Champawat");
            city_options.add("Dehradun");
            city_options.add("Haridwar");
            city_options.add("Nainital");
            city_options.add("Pauri Garhwal");
            city_options.add("Pithoragarh");
            city_options.add("Rudraprayag");
            city_options.add("Tehri Garhwal");
            city_options.add("Udham Singh Naga");
            city_options.add("Uttarkashi");     city_options.add("others");
        }
        else if(stateName.equals("West Bengal")) {
            //West Bengal
            city_options.add("Bankura");
            city_options.add("Barddhaman");
            city_options.add("Birbhum");
            city_options.add("Dakshin Dinajpur");
            city_options.add("Darjiling");
            city_options.add("Haora");
            city_options.add("Hugli");
            city_options.add("Jalpaiguri");
            city_options.add("Koch Bihar");
            city_options.add("Kolkata");
            city_options.add("Maldah");
            city_options.add("Murshidabad");
            city_options.add("Nadia");
            city_options.add("North Twenty Four Parganas");
            city_options.add("Paschim Medinipur");
            city_options.add("Purba Medinipur");
            city_options.add("Puruliya");
            city_options.add("South Twenty Four Parganas");
            city_options.add("Uttar Dinajpur");
            city_options.add("others");
        }
       /* else
        {
            state_options.add("Default1");
            state_options.add("Default2");
            state_options.add("Default3");
            //you can also get a cursor and add Strings as options to city_options instead of what i have done
        }
*/
        ArrayAdapter<String> stateAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.spinnertext,city_options);
        spinner_city.setAdapter(stateAdapter);
    }

}