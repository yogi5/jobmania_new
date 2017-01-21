package com.youngindia.jobportal.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import android.os.Environment;
import android.os.PowerManager;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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


public class fragmnet_emp_reg2 extends Fragment  {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ProgressDialog pDialog;
    String image,uploadresume;
    SeekBar seekBar_experience, seekBar_expected_salry;
    TextView textViewProgress,textView_expectedSalry;
    EditText spinnervalue;
    PowerManager.WakeLock wakeLock;
    EditText edt_code;
    ProgressDialog dialog;

    private final static String FRAGMENT_TAG = "FRAGMENTB_TAG";
    TextView tvFileName;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Bitmap bitmap,bitmap1;
    Button btn_submit;
    Button btn_upload,btn_uploadimg;
    fragment_employee_registration frgempaddress;
    SessionManager session;
    String jobpref="fulltime",phychal="no";
    employee_registration1 employee_registration1 ;
    ImageView profile_img,edit_pic,edit_resume;
    private static final int PICK_FILE_REQUEST = 2;
    private  final String TAG = fragmnet_emp_reg2.class.getSimpleName();
    private String selectedFilePath;
    private OnFragmentInteractionListener mListener;
    frgmnt_setting frgmnt_setting;
    EditText langknown;
    Employee_RegistrationActivity employee_registrationActivity;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private String userChoosenTask;
    LinearLayout llchangeresume,llchangepic;
    String name,mobile,username,password, status, userstatus, franchisevalue,loginstatus,email,tokenvalue;
    Boolean isAvailable=false;
    RadioGroup jobpre,physicallych;
    RadioButton fulltime,parttime,phyes,phyno;
    Spinner category;
    String highereducation,highereducationuniversity,higherEducationPercentage,higherEducationYearPassing,highereducation1,highereducationuniversity1,higherEducationPercentage1,higherEducationYearPassing1
            ,secondry_percentage,secondry_yearPassing,tenth_percentage,tenth_yearPassing,otherEducation_name,otherEducation_percentage,otherEducation_yearPassing,otherEducation_university,per_address, per_pin, per_state,per_city,cur_address, cur_city,cur_pin,cur_state;
    String keyskill1,locationprfer,locationspinnerval, industyprefer,industyspinner,locationother,candidatesttus1,industryother,currentcom,
            currentdes,currentauto,expectedauto,username1;
    public fragmnet_emp_reg2() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public  fragmnet_emp_reg2 newInstance(String param1, String param2) {
        fragmnet_emp_reg2 fragment = new fragmnet_emp_reg2();
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
        /*Bundle bundle = this.getArguments();
        name= bundle.getString("name");
        mobile=bundle.getString("mobileno");
        username=bundle.getString("username");
        password=bundle.getString("password");
        status= bundle.getString("status");
        userstatus=bundle.getString("userstatus");
        franchisevalue=bundle.getString("franchisevalue");
        loginstatus= bundle.getString("loginstatus");*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview=inflater.inflate(R.layout.fragment_fragmnet_emp_reg2, container, false);
        frgmnt_setting=new frgmnt_setting();
        employee_registration1=new employee_registration1();
        pDialog=new ProgressDialog(getActivity());
        btn_submit=(Button)rootview.findViewById(R.id.btn_submit);
        langknown=(EditText)rootview.findViewById(R.id.edt_languages);
        jobpre=(RadioGroup)rootview.findViewById(R.id.radio_btn_group_job_perfernce);
        physicallych=(RadioGroup)rootview.findViewById(R.id.radio_btn_group_physically_challeged);
        fulltime=(RadioButton)rootview.findViewById(R.id.radioButton_full_time);
        parttime=(RadioButton)rootview.findViewById(R.id.radioButton_part_time);
        phyes=(RadioButton)rootview.findViewById(R.id.radiobtn_yes);
        phyno=(RadioButton)rootview.findViewById(R.id.radiobtn_no);
        jobpre.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId)
                {
                    case R.id.radioButton_full_time:
                        fulltime.isChecked();
                        jobpref="fulltime";

//
                        break;
                    case R.id.radioButton_part_time:
                        parttime.isChecked();
                        jobpref="parttime";
                        break;
                }
            }
        });
        physicallych.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId)
                {
                    case R.id.radiobtn_yes:
                        fulltime.isChecked();
                        phychal="yes";
//
                        break;
                    case R.id.radiobtn_no:
                        parttime.isChecked();
                        phychal="no";
                        break;
                }
            }
        });

        category=(Spinner)rootview.findViewById(R.id.spinner_category);
        btn_upload=(Button)rootview.findViewById(R.id.btn_upload);
        edit_resume=(ImageView)rootview.findViewById(R.id.resume_edit);
        llchangeresume=(LinearLayout)rootview.findViewById(R.id.linearlayoutchangeresume);
        llchangepic=(LinearLayout) rootview.findViewById(R.id.linearlayoutchangepic);
        edt_code=(EditText)rootview.findViewById(R.id.edt_code);

        profile_img=(ImageView)rootview.findViewById(R.id.imageView_chnageprofilepic);
        edit_pic=(ImageView)rootview.findViewById(R.id.imageView_editPic);
        btn_uploadimg=(Button)rootview.findViewById(R.id.imageView_chnagePic1);
        btn_uploadimg.setVisibility(View.VISIBLE);
        btn_uploadimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isAvailable=false;
                selectImage();
                profile_img.setVisibility(View.VISIBLE);
                llchangepic.setVisibility(View.VISIBLE);
                btn_uploadimg.setVisibility(View.GONE);
                edit_pic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectImage();
                    }
                });
            }
        });
        tvFileName = (TextView) rootview.findViewById(R.id.tv_file_name);
        seekBar_experience= (SeekBar) rootview.findViewById(R.id.seekBar_Experience);
        textViewProgress = (TextView)rootview.findViewById(R.id.textProgress);
//        spinnervalue=(EditText)rootview.findViewById(R.id.edit_experience);
        //  seekBar_expected_salry= (SeekBar) rootview.findViewById(R.id.seekBar_ExpectedSalary);
        // textView_expectedSalry= (TextView)rootview.findViewById(R.id.textProgressSalry);
        //      String value = spinnervalue.getText().toString();
        // seekBar_experience.setOnSeekBarChangeListener(this);
        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isAvailable=true;
                selectImage();
                //showFileChooser();
            }
        });
      /*  if(btn_upload.getText().toString().trim().equals("Resume Uploaded Sucessfully"))
        {
            btn_upload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openfile(selectedFilePath);
                }
            });

        }
        else if(btn_upload.getText().toString().trim().equals("Upload Resume"))
        {

            btn_upload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showFileChooser();
                }
            });
        }*/
////        seekBar_experience.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
////            @Override
////            public void onStopTrackingTouch(SeekBar seekBar) {
////            }
////
////            @Override
////            public void onStartTrackingTouch(SeekBar seekBar) {
////            }
////
////            @Override
////            public void onProgressChanged(SeekBar seekBar, int progress,
////                                          boolean fromUser) {
////                //---change the font size of the EditText---
////
////                spinnervalue.setText(String.valueOf(progress));
////            }
////        });
////
////        spinnervalue.addTextChangedListener(new TextWatcher() {
//
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                try {
//                    //Update Seekbar value after entering a number
//                    seekBar_experience.setProgress(Integer.parseInt(s.toString()));
//                    spinnervalue.setSelection(spinnervalue.getText().length());
//
//                } catch (Exception ex) {
//                }
//            }
//        });
//        int progress = Integer.parseInt(value);

        // seekBar_expected_salry.setOnSeekBarChangeListener(this);

        session = new SessionManager(getActivity().getApplicationContext());
        HashMap<String, String> user2=session.gettoken();
        tokenvalue=user2.get(SessionManager.KEY_TOKEN);
        HashMap<String, String> user3=session.getUsername1();
        username1=user3.get(SessionManager.KEY_NAME2);
        HashMap<String, String> user1=session.getstoredetails();
        name = user1.get(SessionManager.KEY_NAME1);
        mobile=user1.get(SessionManager.KEY_MOBILE);
        email=user1.get(SessionManager.KEY_EMAIL);
        status=user1.get(SessionManager.KEY_STATUS);

        username=user1.get(SessionManager.KEY_USERNAME1);
        password=user1.get(SessionManager.KEY_PASSWORD);
        /*franchisevalue=user1.get(SessionManager.KEY_FRANCHISEVALUE);*/
        loginstatus=user1.get(SessionManager.KEY_LOGIN);
        HashMap<String, String> user6=session.geteducationname();
        highereducation=user6.get(SessionManager.KEY_HIGHEREDUCATION);
        highereducationuniversity=user6.get(SessionManager.KEY_HIGHEREDUCATIONUniversity);
        higherEducationPercentage=user6.get(SessionManager.KEY_HIGHEREDUCATIONPercetage);
        higherEducationYearPassing=user6.get(SessionManager.KEY_HIGHEREDUCATIONyearofpassing);

//        HashMap<String, String> user23=session.getbachelorqualificationdegree();
//        highereducation1= user23.get(SessionManager.SET_Bachelerdegname);
//        highereducationuniversity1=user23.get(SessionManager.SET_Bachelerdeguniversity);
//        higherEducationPercentage1=user23.get(SessionManager.SET_Bachelerdegyearofpassing);
//        higherEducationYearPassing1=user23.get(SessionManager.SET_Bachelerdegpercentage);



        HashMap<String, String> user21=session.getstoreeducationadetails();
        secondry_percentage=user21.get(SessionManager.KEY_secondry_percentage);
        secondry_yearPassing=user21.get(SessionManager.KEY_secondry_yearPassing);
        tenth_percentage=user21.get(SessionManager.KEY_tenth_percentage);
        tenth_yearPassing=user21.get(SessionManager.KEY_tenth_yearPassing);
        otherEducation_name=user21.get(SessionManager.KEY_otherEducation_name);
        otherEducation_percentage=user21.get(SessionManager.KEY_otherEducation_percentage);
        otherEducation_yearPassing=user21.get(SessionManager.KEY_otherEducation_yearPassing);
        otherEducation_university=user21.get(SessionManager.KEY_otherEducation_university);

        HashMap<String, String> user31=session.getstoreaddress();
        per_address = user31.get(SessionManager.KEY_PERMANENTADDRESS);
        per_city=user31.get(SessionManager.KEY_PERMANENTCITY);
        per_pin=user31.get(SessionManager.KEY_PERMANENTPIN);
        per_state=user31.get(SessionManager.KEY_PERMANENTSTATE);
        cur_address=user31.get(SessionManager.KEY_CURRENTADDRESS);
        cur_city=user31.get(SessionManager.KEY_CURRENTCITY);
        cur_pin=user31.get(SessionManager.KEY_CURRENTPIN);
        cur_state=user31.get(SessionManager.KEY_CURRENTSTATE);

        HashMap<String, String> user41=session.getstorekeydetails();
        keyskill1 = user41.get(SessionManager.KEY_SKILL);
        locationprfer=user41.get(SessionManager.KEY_LOCATIONPREFER);
        locationspinnerval=user41.get(SessionManager.KEY_LOCATIONSPINNER);
        industyprefer=user41.get(SessionManager.KEY_INDUSTRYPREFER);
        industyspinner=user41.get(SessionManager.KEY_INDUSRTYSPINNER);
        locationother=user41.get(SessionManager.KEY_LOCATIONOTHER);
        candidatesttus1=user41.get(SessionManager.KEY_CANDIDATESTATUS);
        industryother=user41.get(SessionManager.KEY_INDUSTRYOTHER);
        currentcom=user41.get(SessionManager.KEY_CURRENTCOM);
        currentdes=user41.get(SessionManager.KEY_CURRENTDES);
        currentauto=user41.get(SessionManager.KEY_CURRENTAUTO);
        expectedauto=user41.get(SessionManager.KEY_EXPECTEDAUTO);



        /*if(session.getflag()==true) {

            HashMap<String, String> user = session.getstorelanguageknown();
            String lanknown = user.get(SessionManager.KEY_LANGUAGEKNOWN);
            String  jobprfer1=user.get(SessionManager.KEY_JOBPREF);
            String cate=user.get(SessionManager.KEY_CATEGORY);
            String phy=user.get(SessionManager.KEY_PHYCHAL);
            langknown.setText(lanknown);
           if(jobprfer1.equals("fulltime"))
            {
                fulltime.setChecked(true);
            }
            else
            {
                parttime.setChecked(true);
            }
            category.setSelection(Integer.parseInt(cate));
            if(phy.equals("yes"))
            {
                phyes.setChecked(true);
            }
            else
            {
                phyno.setChecked(true);
            }
        }*/



        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                    image = getStringImage(bitmap);
                    // uploadresume=getStringImage(bitmap1);
                }catch(Exception e){
                    e.printStackTrace();
                }
                if(selectedFilePath != null){
                    dialog = ProgressDialog.show(getActivity(),"","Uploading File...",true);

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            //creating new thread to handle Http Operations
                            uploadFile(selectedFilePath,username1);
                        }
                    }).start();
                }else{
                    //Toast.makeText(getActivity(),"Please choose a File First",Toast.LENGTH_SHORT).show();
                }
                String count="active";
                String Languageknown=langknown.getText().toString().trim();
                String Category=String.valueOf(category.getSelectedItemPosition());
                session = new SessionManager(getActivity().getApplicationContext());
                session.storelanguageknown(Languageknown,jobpref,Category,phychal);
                registerUser(highereducation,username1,count,highereducationuniversity,higherEducationPercentage,higherEducationYearPassing,secondry_percentage,secondry_yearPassing
                        , tenth_percentage,tenth_yearPassing,otherEducation_name,otherEducation_percentage,otherEducation_yearPassing,otherEducation_university,
                        per_address, per_pin, per_state,per_city,cur_address, cur_city,cur_pin,cur_state,
                        keyskill1,locationspinnerval, industyprefer,industyspinner,locationother,candidatesttus1,industryother,currentcom,
                        currentdes,currentauto,expectedauto,locationspinnerval,image,selectedFilePath);
                //   ,highereducation,highereducation1

               /* String edtcode=edt_code.getText().toString().trim();
                String s="abc128ed205";
                if(edtcode.equals(s))
                {
                    registerUser(highereducation,highereducation1);
                    // calljson2(data);
                }
                else
                {
                    Toast.makeText(getActivity().getApplicationContext(),"Code not match",Toast.LENGTH_LONG).show();
                }*/
                // Toast.makeText(getActivity().getApplicationContext(),"Code not match",Toast.LENGTH_LONG).show();
                //callinsertimageapi(bitmap);
            }
        });
        // Inflate the layout for this fragment
        return rootview;
    }

    private void selectImage1() {
        final CharSequence[] items = { "Take Photo", "Choose from Gallery",
                "Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Upload Here!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result = Utility.checkPermission(getActivity());

                if (items[item].equals("Take Photo")) {
                    userChoosenTask = "Take Photo";
                    if (result)
                        cameraIntent1();

                } else if (items[item].equals("Choose from Library")) {
                    userChoosenTask = "Choose from Library";
                    if (result)
                        if(isAvailable==true)
                        {
                            showFileChooser();
                        }else{
                            galleryIntent();}

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void cameraIntent1() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
    public int uploadFile(final String selectedFilePath, String username1) {

        int serverResponseCode = 0;

        HttpURLConnection connection;
        DataOutputStream dataOutputStream;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";


        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;
        File selectedFile = new File(selectedFilePath);


        String[] parts = selectedFilePath.split("/");
        final String fileName = parts[parts.length - 1];

        if (!selectedFile.isFile()) {
            dialog.dismiss();

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tvFileName.setText("Source File Doesn't Exist: " + selectedFilePath);
                }
            });
            return 0;
        } else {
            try {
                FileInputStream fileInputStream = new FileInputStream(selectedFile);
                URL url = new URL(AppConfig.SERVERPDF_URL);
                connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);//Allow Inputs
                connection.setDoOutput(true);//Allow Outputs
                connection.setUseCaches(false);//Don't use a cached Copy
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Connection", "Keep-Alive");
                connection.setRequestProperty("ENCTYPE", "multipart/form-data");
                connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
                connection.setRequestProperty("username", username1);
                connection.setRequestProperty("uploaded_file", selectedFilePath);
                //creating new dataoutputstream
                dataOutputStream = new DataOutputStream(connection.getOutputStream());

                //writing bytes to data outputstream
                dataOutputStream.writeBytes(twoHyphens + boundary + lineEnd);
                dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\""
                        + selectedFilePath + "\"" + lineEnd);
                dataOutputStream.writeBytes(lineEnd);
                //returns no. of bytes present in fileInputStream
                bytesAvailable = fileInputStream.available();
                //selecting the buffer size as minimum of available bytes or 1 MB
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                //setting the buffer as byte array of size of bufferSize
                buffer = new byte[bufferSize];

                //reads bytes from FileInputStream(from 0th index of buffer to buffersize)
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                //loop repeats till bytesRead = -1, i.e., no bytes are left to read
                while (bytesRead > 0) {
                    //write the bytes read from inputstream
                    dataOutputStream.write(buffer, 0, bufferSize);
                    bytesAvailable = fileInputStream.available();
                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                }

                dataOutputStream.writeBytes(lineEnd);
                dataOutputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                serverResponseCode = connection.getResponseCode();
                String serverResponseMessage = connection.getResponseMessage();

                Log.i(TAG, "Server Response is: " + serverResponseMessage + ": " + serverResponseCode);

                //response code of 200 indicates the server status OK
                if (serverResponseCode == 200) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvFileName.setText("File Upload completed."  + fileName);
                        }
                    });
                }

                //closing the input and output streams
                fileInputStream.close();
                dataOutputStream.flush();
                dataOutputStream.close();


            } catch (FileNotFoundException e) {
                e.printStackTrace();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "File Not Found", Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (MalformedURLException e) {
                e.printStackTrace();
                Toast.makeText(getActivity(), "URL error!", Toast.LENGTH_SHORT).show();

            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(getActivity(), "Cannot Read/Write File!", Toast.LENGTH_SHORT).show();
            }
            dialog.dismiss();
            return serverResponseCode;
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        employee_registrationActivity=(Employee_RegistrationActivity) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    @Override
    public void onResume() {
        super.onResume();
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.setTitle("Professional Details");
        employee_registrationActivity. toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
                // Toast.makeText(getActivity().getApplicationContext(), "Back", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showFileChooser() {


        Intent intent = new Intent();
        //sets the select file to all types of files
        intent.setType("*/*");

        // intent.setType(DocumentsContract.Document.MIME_TYPE_DIR);
        //allows to select data and return it
        intent.setAction(Intent.ACTION_GET_CONTENT);
        //starts new activity to select file and return data
        startActivityForResult(Intent.createChooser(intent, "Choose File to Upload.."), PICK_FILE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA) {
//                if (isAvailable == true) {
//                    onCaptureImageResult1(data);
//                } else {
//                    onCaptureImageResult(data);
//                }
                onCaptureImageResult(data);
            }
            else if (requestCode == PICK_FILE_REQUEST) {
                if (data == null) {
                    //no data present
                    return;
                }
                PowerManager powerManager = (PowerManager) getActivity().getSystemService(getActivity().POWER_SERVICE);
                wakeLock = powerManager.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK, TAG);
                wakeLock.acquire();
                Uri selectedFileUri = data.getData();
                selectedFilePath = FilePath.getPath(getActivity(), selectedFileUri);
                Log.i(TAG, "Selected File Path:" + selectedFilePath);

                if (selectedFilePath != null && !selectedFilePath.equals("")) {
                    tvFileName.setText(selectedFilePath);
                    btn_upload.setText("Uploaded and View");
                    if(btn_upload.getText().toString().trim().equals("Uploaded and View")) {
                        llchangeresume.setVisibility(View.VISIBLE);
                        edit_resume.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(isAvailable==true) {
                                    selectImage();
                                }
                            }
                        });
                        btn_upload.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                openfile(selectedFilePath);
                                //if ((edit_resume.getVisibility()==View.INVISIBLE))
                            }
                        });
                    }
                } else {
                    Toast.makeText(getActivity(), "Cannot upload file to server", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

//    private void onCaptureImageResult1(Intent data) {
//        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
//        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//        //thumbnail.compress(Bitmap.CompressFormat.JPEG, 2, bytes);
//         bitmap1 = Bitmap.createScaledBitmap(thumbnail, 100, 50, true);
//        File destination = new File(Environment.getExternalStorageDirectory(),
//                System.currentTimeMillis() + ".jpg");
//
//        FileOutputStream fo;
//        try {
//            destination.createNewFile();
//            fo = new FileOutputStream(destination);
//            fo.write(bytes.toByteArray());
//            fo.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        if (isAvailable==true) {
//            Intent intent = new Intent(getActivity(), FullScreen_resume.class);
//            intent.putExtra("Bitmap", bitmap1);
//            startActivity(intent);
//        }
//    }

    public  void openfile(final  String selectedFilePath)
    {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
        File file = new File(selectedFilePath);
        String extension = android.webkit.MimeTypeMap.getFileExtensionFromUrl(Uri.fromFile(file).toString());
        String mimetype = android.webkit.MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        if (extension.equalsIgnoreCase("") || mimetype == null) {
            // if there is no extension or there is no definite mimetype, still try to open the file
            intent.setDataAndType(Uri.fromFile(file), "text/*");
        } else {
            intent.setDataAndType(Uri.fromFile(file), mimetype);
        }
        // custom message for the intent
        //dialog.dismiss();
        startActivity(Intent.createChooser(intent, "Choose an Application:"));
    }

    private void selectImage() {
        final CharSequence[] items = { "Take Photo", "Choose from Library",
                "Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Upload Here!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result = Utility.checkPermission(getActivity());

                if (items[item].equals("Take Photo")) {
                    userChoosenTask = "Take Photo";
                    if (result)
                        cameraIntent();

                } else if (items[item].equals("Choose from Library")) {
                    userChoosenTask = "Choose from Library";
                    if (result)
                        if(isAvailable==true)
                        {
                            showFileChooser();
                        }else{galleryIntent();}

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
    private void galleryIntent()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);
    }

    private void cameraIntent()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }
    private void onCaptureImageResult(Intent data) {
        File destination;
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 2, bytes);
        if (isAvailable==true) {
            bitmap1 = Bitmap.createScaledBitmap(thumbnail, 50, 50, true);
            destination = new File(Environment.getExternalStorageDirectory(),
                    System.currentTimeMillis() + ".pdf");
        }else{
            bitmap = Bitmap.createScaledBitmap(thumbnail, 50, 50, true);
            destination = new File(Environment.getExternalStorageDirectory(),
                    System.currentTimeMillis() + ".jpg");
        }
        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (isAvailable==true) {
            Intent intent = new Intent(getActivity(), FullScreen_resume.class);
            intent.putExtra("Bitmap", bitmap1);
            startActivity(intent);
        }
        // edit_resume.setImageBitmap(bitmap);}
        else{profile_img.setImageBitmap(bitmap);}
    }

    private void callinsertimageapi(Bitmap bitmap) {

        final String image = getStringImage(bitmap);
        String tag_string_req = "req_registerimage";
        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_INSERTIMAGE, new Response.Listener<String>() {

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
                        Toast.makeText(getActivity().getApplicationContext(), "Successfully image uploaded", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(getActivity().getApplicationContext(), "Image is not uploaded!!!!", Toast.LENGTH_LONG).show();
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
                params.put("image",image);
                return checkParams(params);
            }
            private Map<String, String> checkParams(Map<String, String> map){
                Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<String, String> pairs = (Map.Entry<String, String>)it.next();
                    if(pairs.getValue()==null){
                        map.put(pairs.getKey(), "");
                    }
                }
                return map;
            }

        };
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void onSelectFromGalleryResult(Intent data) {

        Bitmap bm=null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getActivity().getApplicationContext().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        bitmap = Bitmap.createScaledBitmap(bm, 100, 50, true);
        if (isAvailable==true)
        {edit_resume.setImageBitmap(bitmap);}
        else{profile_img.setImageBitmap(bitmap);}
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if(userChoosenTask.equals("Take Photo"))
                        cameraIntent();
                    else if(userChoosenTask.equals("Choose from Library"))
                        galleryIntent();
                } else {
                    //code for deny
                }
                break;
        }
    }
    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }
    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
    private void registerUser(final String highereducation, final String username1, final String count, final String highereducationuniversity, final String higherEducationPercentage, final String higherEducationYearPassing, final String secondry_percentage,
                              final String secondry_yearPassing, final String tenth_percentage, final String tenth_yearPassing, final String otherEducation_name, final String otherEducation_percentage, final String otherEducation_yearPassing, final String otherEducation_university, final String per_address, final String per_pin, final String per_state, final String per_city, final String cur_address, final String cur_city, final String cur_pin, final String cur_state, final String keyskill1, final String locationspinnerval, final String industyprefer, final String industyspinner,
                              final String locationother, final String candidatesttus1, final String industryother, final String currentcom, final String currentdes, final String currentauto, final String expectedauto, final String locationprfer, final String image, final String selectedFilePath) {
        //   , final String highereducation, final String highereducation1
        // Tag used to cancel the request
        String tag_string_req = "req_register";
        // pDialog.setMessage("Registering ...");
        //  showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_USERDETAIL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Register Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = null;
                    try {
                        jObj = new JSONObject(response);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    boolean error = jObj.getBoolean("error");
                    if (!error) {
                        // User successfully stored in MySQL
                        // Now store the user in sqlite
//                        String uid = jObj.getString("uid");
//
//                        JSONObject user = jObj.getJSONObject("user");
//                        String name = user.getString("name");
//                        String email = user.getString("email");
//                        String created_at = user
//                                .getString("created_at");

                        // Inserting row in users table
                        //db.addUser(name, email, uid, created_at);

                     /*   AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                        String alert1 = "username: " + username;
                        String alert2 = "password: " + password;


                        builder1.setMessage( "You successfully registered!!!!" + "\n" + alert1 +"\n" +alert2);
                        builder1.setNeutralButton("OK", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {*/

                        int update_percentage=jObj.getInt("num");
//
                        int update_percentae=jObj.getInt("com");
                        //session.setpercentage(update_percentae);
                        //String highereducation1=jObj.getString("highereducation1");
                        //session.storeedu(highereducation);
                        Intent intent= new Intent(getActivity(),Employee_HomeActivity.class);
                               /* intent.putExtra("Username",username);
                                intent.putExtra("Password",password);
                                intent.putExtra("status",status);*/
                        startActivity(intent);
                        /*    }
                        });
                        builder1.show();*/
                    } else {

                        Toast.makeText(getActivity().getApplicationContext(), "Sorry Server is down,Please try after some time", Toast.LENGTH_LONG).show();

                        /*String errorMsg = jObj.getString("error_msg");
                        final String valueserver=jObj.getString("value");
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());



                        builder1.setMessage(errorMsg);
                        builder1.setNeutralButton("OK", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                FragmentTransaction fragmenttransaction = getActivity().getSupportFragmentManager().beginTransaction();
                                //   sScreen = getResources().getString(R.string.title_wall);
                                fragmenttransaction.replace(R.id.frame_container1,employee_registration1, FRAGMENT_TAG);

                                fragmenttransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                                fragment_employee_registration frg=new fragment_employee_registration();
                                session.storevalue(valueserver);
                                session.setflag(true);
//                                frg.checked=true;
//                                employee_registration1.checked=true;
                                fragmenttransaction.commit();
                            }
                        }); builder1.show();*/
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

                params.put("highereducation",highereducation);
                //params.put("highereducation1",highereducation1);
                params.put("username",username1);
                params.put("count",count);
                params.put("highereducation", highereducation);
                params.put("highereducationuniversity",highereducationuniversity);
                params.put("higherEducationPercentage",higherEducationPercentage);
                params.put("higherEducationYearPassing",higherEducationYearPassing);
                params.put("secondry_percentage",secondry_percentage);
                params.put("secondry_yearPassing",secondry_yearPassing);
                params.put("tenth_percentage",tenth_percentage);
                params.put("tenth_yearPassing",tenth_yearPassing);
                params.put("otherEducation_name",otherEducation_name);
                //
                params.put("otherEducation_percentage", otherEducation_percentage);
                params.put("otherEducation_yearPassing",otherEducation_yearPassing);
                params.put("otherEducation_university",otherEducation_university);
                params.put("per_address",per_address);
                params.put("per_pin",per_pin);
                params.put("per_state",per_state);
                params.put("per_city",per_city);
                params.put("cur_city",cur_city);
                params.put("cur_pin",cur_pin);
                params.put("cur_address",cur_address);

                // params.put("name", name);
                params.put("cur_state",cur_state);
                params.put("keyskill1",keyskill1);
                params.put("locationspinnerval",locationspinnerval);
                params.put("industyprefer",industyprefer);
                params.put("industyspinner",industyspinner);
                params.put("locationother",locationother);
                params.put("candidatesttus1",candidatesttus1);
                params.put("industryother",industryother);
                params.put("currentcom",currentcom);
                params.put("currentdes",currentdes);
                params.put("currentauto",currentauto);
                params.put("expectedauto",expectedauto);
                params.put("locationprfer",locationprfer);
                params.put("image",image);
                params.put("selectedFilePath",selectedFilePath);
                return checkParams(params);


            }
            private Map<String, String> checkParams(Map<String, String> map){
                Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<String, String> pairs = (Map.Entry<String, String>)it.next();
                    if(pairs.getValue()==null){
                        map.put(pairs.getKey(), "");
                    }
                }
                return map;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 40, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, 0);
        return encodedImage;
    }
}