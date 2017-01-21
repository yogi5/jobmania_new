package com.youngindia.jobportal.ui;

//import android.app.Activity;
//import android.app.AlertDialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.graphics.Bitmap;
//import android.media.Image;
//import android.net.Uri;
//import android.os.Bundle;
//import android.os.Environment;
//import android.provider.MediaStore;
//import android.support.v4.app.Fragment;
//import android.support.v7.app.ActionBar;
//import android.support.v7.app.AppCompatActivity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.Toast;
//
//import com.youngindia.jobportal.CompanylogoChange;
//import com.youngindia.jobportal.R;
//
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//
///**
// * A simple {@link Fragment} subclass.
// * Activities that contain this fragment must implement the
// * {@link OnFragmentInteractionListener} interface
// * to handle interaction events.
// * Use the {@link frgmnt_setting#newInstance} factory method to
// * create an instance of this fragment.
// */
//public class frgmnt_setting extends Fragment {
//    Employee_HomeActivity employee_homeActivity;
//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    ImageView  pic_change,edit_pic;
//    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
//
//    private String userChoosenTask;
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    private OnFragmentInteractionListener mListener;
//
//    public frgmnt_setting() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment frgmnt_setting.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static frgmnt_setting newInstance(String param1, String param2) {
//        frgmnt_setting fragment = new frgmnt_setting();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//
//        getActivity().getWindow().getDecorView().setSystemUiVisibility(View.GONE);
//        View rootview=inflater.inflate(R.layout.fragment_frgmnt_setting, container, false);
//        pic_change= (ImageView) rootview.findViewById(R.id.imageView_chnagePic1);
//        edit_pic= (ImageView) rootview.findViewById(R.id.imageView_chnagePic);
//        edit_pic.bringToFront();
//        edit_pic.setClickable(true);
//        edit_pic.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
////                Intent intent=new Intent(getActivity(), CompanylogoChange.class);
////                startActivity(intent);
//                //Toast.makeText(getActivity(),"gg",Toast.LENGTH_SHORT).show();
//                selectImage();
//            }
//        });
//
//
//        return rootview;
//    }
//
//
//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }
//
//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        employee_homeActivity=(Employee_HomeActivity) activity;
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p/>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
//    @Override
//    public void onResume() {
//        super.onResume();
//        AppCompatActivity activity = (AppCompatActivity) getActivity();
//        ActionBar actionBar = activity.getSupportActionBar();
//        actionBar.setTitle("Profile");
//    }
//    private void selectImage() {
//        final CharSequence[] items = { "Take Photo", "Choose from Library",
//                "Cancel" };
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setTitle("Add Photo!");
//        builder.setItems(items, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int item) {
//                boolean result = Utility.checkPermission(getActivity());
//
//                if (items[item].equals("Take Photo")) {
//                    userChoosenTask = "Take Photo";
//                    if (result)
//                        cameraIntent();
//
//                } else if (items[item].equals("Choose from Library")) {
//                    userChoosenTask = "Choose from Library";
//                    if (result)
//                        galleryIntent();
//
//                } else if (items[item].equals("Cancel")) {
//                    dialog.dismiss();
//                }
//            }
//        });
//        builder.show();
//    }
//    private void galleryIntent()
//    {
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);//
//        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);
//    }
//
//    private void cameraIntent()
//    {
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        startActivityForResult(intent, REQUEST_CAMERA);
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (resultCode == Activity.RESULT_OK) {
//            if (requestCode == SELECT_FILE)
//                onSelectFromGalleryResult(data);
//            else if (requestCode == REQUEST_CAMERA)
//                onCaptureImageResult(data);
//        }
//    }
//    private void onCaptureImageResult(Intent data) {
//        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
//        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//        //thumbnail.compress(Bitmap.CompressFormat.JPEG, 2, bytes);
//        Bitmap bitmap = Bitmap.createScaledBitmap(thumbnail, 100, 100, true);
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
//
//       pic_change.setImageBitmap(bitmap);
//    }
//    private void onSelectFromGalleryResult(Intent data) {
//
//        Bitmap bm=null;
//        if (data != null) {
//            try {
//                bm = MediaStore.Images.Media.getBitmap(getActivity().getApplicationContext().getContentResolver(), data.getData());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        Bitmap bitmap = Bitmap.createScaledBitmap(bm, 100, 100, true);
//        pic_change.setImageBitmap(bitmap);
//    }
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        switch (requestCode) {
//            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    if(userChoosenTask.equals("Take Photo"))
//                        cameraIntent();
//                    else if(userChoosenTask.equals("Choose from Library"))
//                        galleryIntent();
//                } else {
//                    //code for deny
//                }
//                break;
//        }
//    }
//}
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.youngindia.jobportal.CompanylogoChange;
import com.youngindia.jobportal.R;
import com.youngindia.jobportal.database.SessionManager;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static com.youngindia.jobportal.R.drawable.v;
import static com.youngindia.jobportal.R.id.imageView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link frgmnt_setting#newInstance} factory method to
 * create an instance of this fragment.
 */
public class frgmnt_setting extends Fragment {
    Employee_HomeActivity employee_homeActivity;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    ImageView pic_change, edit_pic;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;


    private static final int CAMERA_CODE = 101, GALLERY_CODE = 201, CROPING_CODE = 301;
    private Uri mImageCaptureUri;
    private final static int REQUEST_PERMISSION_REQ_CODE = 34;
    private File outPutFile = null;
    Bitmap photo;

    private String userChoosenTask;

    Button btn;
    String image;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public frgmnt_setting() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment frgmnt_setting.
     */
    // TODO: Rename and change types and number of parameters
    public static frgmnt_setting newInstance(String param1, String param2) {
        frgmnt_setting fragment = new frgmnt_setting();
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

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getActivity().getWindow().getDecorView().setSystemUiVisibility(View.GONE);
        View rootview = inflater.inflate(R.layout.fragment_frgmnt_setting, container, false);
        pic_change = (ImageView) rootview.findViewById(R.id.imageView_chnagePic1);
        outPutFile = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");
        edit_pic = (ImageView) rootview.findViewById(R.id.imageView_chnagePic);
        btn = (Button) rootview.findViewById(R.id.btn_submit);
        edit_pic.bringToFront();
        edit_pic.setClickable(true);

        edit_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {

                    image = getStringImage(photo);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return rootview;
    }


    // btn= (Button)rootview.findViewById(R.id.btn_compny_submit);


    /*  btn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              try {

                 // image = getStringImage(photo);

              } catch (Exception e) {
                  e.printStackTrace();
              }
          }
          });

      }*/
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        employee_homeActivity = (Employee_HomeActivity) activity;
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


   /* public void onResume() {
        super.onResume();
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.setTitle("Profile");
    }*/


    private void selectImage() {

        final CharSequence[] items = {"Capture Photo", "Choose from Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (items[item].equals("Capture Photo")) {

                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp1.jpg");
                    mImageCaptureUri = Uri.fromFile(f);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
                    startActivityForResult(intent, CAMERA_CODE);

                } else if (items[item].equals("Choose from Gallery")) {

                    Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(i, GALLERY_CODE);

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    public void onResume() {
        super.onResume();
        if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSION_REQ_CODE);
            return;
        }
    }

    public void onRequestPermissionsResult(final int requestCode, final @NonNull String[] permissions, final @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION_REQ_CODE: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getActivity(), "Permission granted.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Permission denied.", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_CODE && resultCode == RESULT_OK && null != data) {

            mImageCaptureUri = data.getData();
            System.out.println("Gallery Image URI : " + mImageCaptureUri);
            CropingIMG();

        } else if (requestCode == CAMERA_CODE && resultCode == RESULT_OK) {

            System.out.println("Camera Image URI : " + mImageCaptureUri);
            CropingIMG();
        } else {
            if (requestCode == CROPING_CODE) {

                try {
                    if (outPutFile.exists()) {
                        photo = decodeFile(outPutFile);

                        pic_change.setImageBitmap(photo);
                    } else {
                        Toast.makeText(getActivity(), "Error while save image", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void CropingIMG() {

        final ArrayList<CropingOption> cropOptions = new ArrayList<CropingOption>();

        //  Intent intent1 = new Intent("android.intent.Gallery.CROP");
        //    intent.setType("image/*");
        //intent.addCategory("android.intent.category.APP_GALLERY");
        //intent.setType("image/*");
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setType("image/*");
        //startActivity(intent);
        List<ResolveInfo> list = getActivity().getPackageManager().queryIntentActivities(intent, 0);
        //List<ResolveInfo> list = getActivity().getPackageManager().queryIntentActivities(intent, 0);
        int size = list.size();
        if (size == 0) {
            Toast.makeText(getActivity(), "Cann't find image croping app", Toast.LENGTH_SHORT).show();
            return;
        } else {
            //        if(size == 1){
            //      if(size == 1){
            intent.setData(mImageCaptureUri);
            // intent.putExtra(“crop”, “true”);
            intent.putExtra("outputX", 512);
            intent.putExtra("outputY", 512);
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            intent.putExtra("scale", true);

            //TODO: don't use return-data tag because it's not return large image data and crash not given any message
            //intent.putExtra("return-data", true);

            //Create output file here
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(outPutFile));

            if (size == 1) {
                Intent i = new Intent(intent);
                ResolveInfo res = (ResolveInfo) list.get(0);

                i.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));

                startActivityForResult(i, CROPING_CODE);
            } else {
                for (ResolveInfo res : list) {
                    final CropingOption co = new CropingOption();

                    co.title = getActivity().getPackageManager().getApplicationLabel(res.activityInfo.applicationInfo);
                    co.icon = getActivity().getPackageManager().getApplicationIcon(res.activityInfo.applicationInfo);
                    co.appIntent = new Intent(intent);
                    co.appIntent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
                    cropOptions.add(co);
                }

                CropingOptionAdapter adapter = new CropingOptionAdapter(getActivity(), cropOptions);

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Choose Croping App");
                builder.setCancelable(false);
                builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        // startActivityForResult( cropOptions.get(item).appIntent, CROPING_CODE);
                        startActivityForResult(cropOptions.get(item).appIntent, CROPING_CODE);
                    }
                });

                builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {

                        if (mImageCaptureUri != null) {
                            getActivity().getContentResolver().delete(mImageCaptureUri, null, null);
                            mImageCaptureUri = null;
                        }
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }
        }
        }


    private Bitmap decodeFile(File f) {
        try {
            // decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(f), null, o);

            // Find the correct scale value. It should be the power of 2.
            final int REQUIRED_SIZE = 1024;
            int width_tmp = o.outWidth, height_tmp = o.outHeight;
            int scale = 1;
            while (true) {
                if (width_tmp / 2 < REQUIRED_SIZE || height_tmp / 2 < REQUIRED_SIZE)
                    break;
                width_tmp /= 2;
                height_tmp /= 2;
                scale *= 2;
            }

            // decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
        } catch (FileNotFoundException e) {
        }
        return null;
    }






/*        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};
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
                    if (result) {
                        galleryIntent();
                    }

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }*/

    private void galleryIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);
    }

    private void cameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        //thumbnail.compress(Bitmap.CompressFormat.JPEG, 2, bytes);
        Bitmap bitmap = Bitmap.createScaledBitmap(thumbnail, 150, 150, true);
        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");

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
        // profile_img.setImageBitmap(bitmap);
    }

    private void onSelectFromGalleryResult(Intent data) {

        Bitmap bm = null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Bitmap bitmap = Bitmap.createScaledBitmap(bm, 150, 150, true);
        // profile_img.setImageBitmap(bitmap);
    }

      /*  public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (userChoosenTask.equals("Take Photo"))
                        cameraIntent();
                    else if (userChoosenTask.equals("Choose from Library"))
                        galleryIntent();
                } else {
                    //code for deny
                }
                break;
        }
    } */

    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 40, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, 0);
        return encodedImage;
    }
}
