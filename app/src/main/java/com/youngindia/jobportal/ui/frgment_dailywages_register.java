package com.youngindia.jobportal.ui;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
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
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link frgment_dailywages_register#newInstance} factory method to
 * create an instance of this fragment.
 */
public class frgment_dailywages_register extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    EditText dailywagername,dailywagercontactno;
    DailyWagesRegstr  dailyWages_MainActivity;
    private static final String TAG = frgment_dailywages_register.class.getSimpleName();
    Button btndialywager;
    int Count=0;
    /*Spinner  dailyoccupation,dailylocation;
    RadioButton male_radiobtn,female_radiobtn;
    RadioGroup radioGroup_gender;*/
    String Gender="";
    ProgressDialog pDialog;
    AppController mInstance;
    EditText otherlocality;
    Spinner  dailyoccupation,dailylocation,dailycity,dailylocality,daily_wages;
    final ArrayList<String> state_options=new ArrayList<String>();
    final ArrayList<String> city_options=new ArrayList<String>();
    final ArrayList<String> state_options1=new ArrayList<String>();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String skillofdailywagers="";
    String locality="";
    LinearLayout llskilled,llwages,lllocality;
    String value="";
    RadioButton male_radiobtn,female_radiobtn,skilled_radiobtn,unskilled_radiobtn;
    RadioGroup radioGroup_gender,radioSkilledGroup;
    LinearLayout linearLayout_city;
    private OnFragmentInteractionListener mListener;
    SessionManager session;
    public frgment_dailywages_register() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment frgment_dailywages_register.
     */

    // TODO: Rename and change types and number of parameters
    public static frgment_dailywages_register newInstance(String param1, String param2) {
        frgment_dailywages_register fragment = new frgment_dailywages_register();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview=inflater.inflate(R.layout.fragment_frgment_dailywages_register, container, false);
        session = new SessionManager(getActivity().getApplicationContext());
        btndialywager=(Button) rootview.findViewById(R.id.btndailywagerregister);
        dailywagername=(EditText)  rootview.findViewById(R.id.Ed_name);
        dailywagercontactno=(EditText)  rootview.findViewById(R.id.Ed_contact);
        dailyoccupation=(Spinner)rootview.findViewById(R.id.spinner_profession);

        dailylocation=(Spinner)rootview.findViewById(R.id.spinner_location);
        linearLayout_city= (LinearLayout) rootview.findViewById(R.id.linearlayout_city);

        radioGroup_gender= (RadioGroup)rootview.findViewById(R.id.radiogroup_gender);
        dailycity=(Spinner)rootview.findViewById(R.id.spinner_city);
        llwages=(LinearLayout) rootview.findViewById(R.id.linearlayout_dailywages);
        //   lllocality=(LinearLayout) rootview.findViewById(R.id.linearlayout_locality);
        //    dailylocality=(Spinner)rootview.findViewById(R.id.spinner_locality);
//        dailylocality.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                value=parent.getSelectedItem().toString();
//                if(value.equals("Others"))
//                {
//                    value="true";
//                    lllocality.setVisibility(View.GONE);
//                    otherlocality.setVisibility(View.VISIBLE);
//                }else{
//                    value="false";
//                    lllocality.setVisibility(View.VISIBLE);
//                    otherlocality.setVisibility(View.GONE);
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
        daily_wages=(Spinner)rootview.findViewById(R.id.spinner_wages);
        otherlocality=(EditText) rootview.findViewById(R.id.otherlocality);
        male_radiobtn = (RadioButton) rootview.findViewById(R.id.radioButton_male);
        female_radiobtn = (RadioButton) rootview.findViewById(R.id.radioButton_female);
        pDialog = new ProgressDialog(getActivity());
        pDialog.setCancelable(false);
        mInstance=new AppController();

        radioGroup_gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId)
                {
                    case R.id.radioButton_male:
                        male_radiobtn.isChecked();
                        Gender="male";
                        break;
                    case R.id.radioButton_female:
                        female_radiobtn.isChecked();
                        Gender="female";
                        break;
                }
            }
        });
        radioSkilledGroup=(RadioGroup) rootview.findViewById(R.id.radiogroup_skilled);
        skilled_radiobtn=(RadioButton)  rootview.findViewById(R.id.radioButton_skill);
        unskilled_radiobtn=(RadioButton)  rootview.findViewById(R.id.radioButton_unskill);
        llskilled=(LinearLayout)  rootview.findViewById(R.id.linerlayout_profession);

        radioSkilledGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId)
                {
                    case R.id.radioButton_skill:
                        skilled_radiobtn.isChecked();
                        skillofdailywagers="skilled";
                        llwages.setVisibility(View.GONE);
                        llskilled.setVisibility(View.VISIBLE);
                        break;
                    case R.id.radioButton_unskill:
                        unskilled_radiobtn.isChecked();
                        skillofdailywagers="unskilled";
                        llwages.setVisibility(View.VISIBLE);
                        llskilled.setVisibility(View.GONE);
                        break;
                }
            }
        });

//
//        state_options.add("Bangalore");
//        state_options.add("Mangalore");
//        state_options.add("Hubli");
//        state_options.add("Bijapur");
//        state_options.add("Ranchi");
//        state_options.add("Bokaro");
//        state_options.add("Giridih");
//
//
//
//        city_options.add("Domlur Layout");
//        city_options.add("Indira Nagar");
//        city_options.add("Old Airport Road");
//        city_options.add("Marathahalli");
//        city_options.add("Seshadripuram");
//        city_options.add("Yelahanka");
//        city_options.add("Others");
//        city_options.add("New Barganda");
//        city_options.add("R R Colony");
//        city_options.add("Makatpur");
//        city_options.add("Others");



//        state_options1.add("Karnataka");
//        state_options1.add("Jharkhand");

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

        ArrayAdapter<String> cityAdapter1 = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.spinnertext,state_options1);
        dailylocation.setAdapter(cityAdapter1);

//        ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.spinnertext,city_options);
//        dailylocality.setAdapter(cityAdapter);

        ArrayAdapter<String> stateAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.spinnertext,city_options);
        dailycity.setAdapter(stateAdapter);

        dailylocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View view,
                                       int position, long id) {

                String stateName1=state_options1.get(position).toString();
                linearLayout_city.setVisibility(View.VISIBLE);
                otherlocality.setVisibility(View.GONE);
                resetCity1(stateName1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

        dailycity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

               // String stateName=state_options1.get(position).toString();
                // resetCity1(stateName);
                value = parent.getSelectedItem().toString();
                if (value.equals("others")) {
                    value = "true";
                    linearLayout_city.setVisibility(View.GONE);
                    otherlocality.setVisibility(View.VISIBLE);
                } else {
                    value = "false";
                    linearLayout_city.setVisibility(View.VISIBLE);
                    otherlocality.setVisibility(View.GONE);
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });
        btndialywager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String occupation="";
                String dailywages="";
                String name = dailywagername.getText().toString().trim();
                String contactno = dailywagercontactno.getText().toString().trim();
                if(skillofdailywagers.equals("skilled"))
                {
                    dailywages="0000";
                    occupation =dailyoccupation.getSelectedItem().toString().trim();
                }else{
                    occupation="unskilled";
                    dailywages=daily_wages.getSelectedItem().toString().trim();
                }

              /*  String name = dailywagername.getText().toString().trim();
                String contactno = dailywagercontactno.getText().toString().trim();
                String occupation=dailyoccupation.getSelectedItem().toString().trim();*/
                String state = dailylocation.getSelectedItem().toString().trim();
                String city=dailycity.getSelectedItem().toString().trim();
                if (value.equals("true"))
                {
                    locality=otherlocality.getText().toString().trim();
                    session.setotherlocality(locality,state);
                }else{
                    locality=dailycity.getSelectedItem().toString().trim();
                }

                locality = locality.replace(" ", "");
                locality = locality.replaceAll("[\\-\\+\\.\\^:,]","");
                occupation = occupation.replace(" ", "");
                occupation = occupation.replaceAll("[\\-\\+\\.\\^:,]","");
                dailywages = dailywages.replace(" ", "");
                dailywages = dailywages.replaceAll("[\\-\\+\\.\\^:,]","");
                if (!name.isEmpty() && !contactno.isEmpty() && !occupation.isEmpty() && !locality.isEmpty()
                        && !Gender.isEmpty() && !dailylocation.getSelectedItem().toString().trim().equals("Select Location")&& !daily_wages.getSelectedItem().toString().trim().equals("Select wages")
                        ) {

                    registerUser(name, contactno, occupation, state,locality,Gender,dailywages);
                } else {
                    Toast.makeText(getActivity().getApplicationContext(),
                            "Please enter your details!", Toast.LENGTH_LONG)
                            .show();

                }
            }
        });
        return rootview;

    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        dailyWages_MainActivity=(DailyWagesRegstr) activity; ;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void registerUser(final String name, final String contactno,
                              final String occupation, final String state,final String location, final String gender, final String dailywages)  {
        // Tag used to cancel the request
        String tag_string_req = "req_register";

        pDialog.setMessage("Registering ...");

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_DAILYWAGERREGISTER, new Response.Listener<String>() {

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

                        //Toast.makeText(getActivity().getApplicationContext(), "Register successfully ", Toast.LENGTH_LONG).show();
                        final AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());


                        builder1.setMessage( "Register Sucessfully" );
                        builder1.setNeutralButton("OK", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                hideDialog();
                            }
                        });
                        builder1.show();

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
                params.put("name", name);
                params.put("contactno", contactno);
                params.put("occupation", occupation);
                params.put("location", location);
                params.put("gender", gender);
                params.put("wages",dailywages);
                params.put("state",state);
                return params;
            }

        };

        // Adding request to request queue
        mInstance.getInstance().addToRequestQueue(strReq, tag_string_req);
    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
    //    public void resetCity(String stateName)
//    {
//        city_options.removeAll(city_options);//i haven't checked this.
//        if(stateName.equals("Bangalore"))
//        {
//            city_options.add("Domlur Layout");
//            city_options.add("Indira Nagar");
//            city_options.add("Old Airport Road");
//            city_options.add("Marathahalli");
//            city_options.add("Seshadripuram");
//            city_options.add("Yelahanka");
//            city_options.add("Others");
//            //you can also get a cursor and add Strings as options to city_options instead of what i have done
//        }
//        else if(stateName.equals("Giridih"))
//        {
//            city_options.add("New Barganda");
//            city_options.add("R R Colony");
//            city_options.add("Makatpur");
//            city_options.add("Others");
//            // you can also get a cursor and add Strings as options to city_options instead of what i have done
//        }
//        else
//        {
//            city_options.add("Others");
//            //you can also get a cursor and add Strings as options to city_options instead of what i have done
//        }
//
//        ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.spinnertext,city_options);
//        dailylocality.setAdapter(cityAdapter);
//    }
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
        {

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
            city_options.add("Wayanad");
            city_options.add("others");
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
            city_options.add("Ukhrul");
            city_options.add("others");
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
            city_options.add("West Khasi Hills");
            city_options.add("others");
        }
        else if(stateName.equals("Mizoram")) {
            //Mizoram
            city_options.add("Aizawl");
            city_options.add("Kolasib");
            city_options.add("Lawngtlai");
            city_options.add("Lunglei");
            city_options.add("Mamit");
            city_options.add("Serchhip");
            city_options.add("Siaha");
            city_options.add("others");
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
            city_options.add("Zunheboto");
            city_options.add("others");
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
            city_options.add("Gajapati");
            city_options.add("others");
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
            city_options.add("Tarn Taran");
            city_options.add("others");
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
            city_options.add("Udaipur");
            city_options.add("others");
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
            city_options.add("Warangal");
            city_options.add("others");
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
            city_options.add("Golaghat");
            city_options.add("others");
        }
        else if(stateName.equals("Tripura")) {
            //Tripura
            city_options.add("Dhalai");
            city_options.add("North Tripura");
            city_options.add("South Tripura");
            city_options.add("West Tripura");
            city_options.add("others");
        }
        else if(stateName.equals("Uttar Pradesh")) {
            //Uttar Pradesh
            city_options.add("Agra");
            city_options.add("Aligarh");
            city_options.add("Allahabad");
            city_options.add("Bijnor");
            city_options.add("Chitrakoot");
            city_options.add("Varanasi");
            city_options.add("others");
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
//        if(stateName.equals("Karnataka"))
//        {
//            state_options.add("Bangalore");
//            state_options.add("Mangalore");
//            state_options.add("Hubli");
//            state_options.add("Bijapur");
//
//            //you can also get a cursor and add Strings as options to city_options instead of what i have done
//        }
//        else if(stateName.equals("Jharkhand"))
//        {
//            state_options.add("Ranchi");
//            state_options.add("Bokaro");
//            state_options.add("Giridih");
//            // you can also get a cursor and add Strings as options to city_options instead of what i have done
//        }
       /* else
        {
            state_options.add("Default1");
            state_options.add("Default2");
            state_options.add("Default3");
            //you can also get a cursor and add Strings as options to city_options instead of what i have done
        }
*/
        ArrayAdapter<String> stateAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.spinnertext,city_options);
        dailycity.setAdapter(stateAdapter);
    }


}