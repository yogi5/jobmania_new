package com.youngindia.jobportal;

import android.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.youngindia.jobportal.database.SessionManager;
import com.youngindia.jobportal.ui.Company_Base;
import com.youngindia.jobportal.ui.CropingOption;
import com.youngindia.jobportal.ui.CropingOptionAdapter;
import com.youngindia.jobportal.ui.app.AppConfig;
import com.youngindia.jobportal.ui.app.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompanylogoChange extends AppCompatActivity {
    private final static int REQUEST_PERMISSION_REQ_CODE = 34;
    private static final int CAMERA_CODE = 101, GALLERY_CODE = 201, CROPING_CODE = 301;

    private Button btn_select_image;
    private ImageView imageView;
    private Uri mImageCaptureUri;
    private File outPutFile = null;
    SessionManager session;
    Bitmap photo;
    String image,companyname;
    ProgressDialog pDialog;
Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_companylogo_change);
        outPutFile = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");
        pDialog=new ProgressDialog(this);
        session=new SessionManager(this);
        btn_select_image = (Button) findViewById(R.id.btn_select_image);
        imageView = (ImageView) findViewById(R.id.img_photo);
b1=(Button) findViewById(R.id.btn_uploadserver);
        btn_select_image.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                selectImageOption();
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try{

                    image = getStringImage(photo);

                }catch(Exception e){
                    e.printStackTrace();
                }
                HashMap<String, String> user1=session.getcompanyname();
                companyname = user1.get(SessionManager.KEY_COMPANYNAMENAME);
                registerUser(companyname,image);

            }
        });

    }
    private void registerUser(final String companyname, final String image) {
        //   , final String highereducation, final String highereducation1
        // Tag used to cancel the request
        String tag_string_req = "req_register";

        pDialog.setMessage("updating ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.updatecompanylogo, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

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

                        /*Toast.makeText(getApplicationContext(),
                                "Sucessfully uploaded", Toast.LENGTH_LONG).show();*/

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

                       AlertDialog.Builder builder1 = new AlertDialog.Builder(CompanylogoChange.this);
                        builder1.setMessage( "Sucessfully uploaded");
                        builder1.setNeutralButton("OK", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                Intent intent= new Intent(getApplicationContext(),Company_Base.class);
                                startActivity(intent);
                            }
                        });
                        builder1.show();
                    } else {

                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                        //final String valueserver=jObj.getString("value");
                      /*  AlertDialog.Builder builder1 = new AlertDialog.Builder(CompanylogoChange.this);
                        builder1.setMessage(errorMsg);
                        builder1.setNeutralButton("OK", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                Toast.makeText(getApplicationContext(),"Please try again!!!",Toast.LENGTH_SHORT).show();
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
              /*  Log.e(TAG, "Registration Error: " + error.getMessage());*/
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                // hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("companyname", companyname);
                params.put("image",image);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }
    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }
    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    private void selectImageOption() {
        final CharSequence[] items = { "Capture Photo", "Choose from Gallery", "Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(CompanylogoChange.this);
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

    @Override
    protected void onResume() {
        super.onResume();
        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSION_REQ_CODE);
            return;
        }
    }

    @Override
    public void onRequestPermissionsResult(final int requestCode, final @NonNull String[] permissions, final @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION_REQ_CODE: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission granted.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Permission denied.", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_CODE && resultCode == RESULT_OK && null != data) {

            mImageCaptureUri = data.getData();
            System.out.println("Gallery Image URI : "+mImageCaptureUri);
            CropingIMG();

        } else if (requestCode == CAMERA_CODE && resultCode == Activity.RESULT_OK) {

            System.out.println("Camera Image URI : "+mImageCaptureUri);
            CropingIMG();
        } else if (requestCode == CROPING_CODE) {

            try {
                if(outPutFile.exists()){
                     photo = decodeFile(outPutFile);
                    imageView.setImageBitmap(photo);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Error while save image", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void CropingIMG() {

        final ArrayList<CropingOption> cropOptions = new ArrayList<CropingOption>();

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setType("image/*");

        List<ResolveInfo> list = getPackageManager().queryIntentActivities( intent, 0 );
        int size = list.size();
        if (size == 0) {
            Toast.makeText(this, "Cann't find image croping app", Toast.LENGTH_SHORT).show();
            return;
        } else {
            intent.setData(mImageCaptureUri);
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
                Intent i   = new Intent(intent);
                ResolveInfo res = (ResolveInfo) list.get(0);

                i.setComponent( new ComponentName(res.activityInfo.packageName, res.activityInfo.name));

                startActivityForResult(i, CROPING_CODE);
            } else {
                for (ResolveInfo res : list) {
                    final CropingOption co = new CropingOption();

                    co.title  = getPackageManager().getApplicationLabel(res.activityInfo.applicationInfo);
                    co.icon  = getPackageManager().getApplicationIcon(res.activityInfo.applicationInfo);
                    co.appIntent= new Intent(intent);
                    co.appIntent.setComponent( new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
                    cropOptions.add(co);
                }

                CropingOptionAdapter adapter = new CropingOptionAdapter(getApplicationContext(), cropOptions);

                AlertDialog.Builder builder = new AlertDialog.Builder(CompanylogoChange.this);
                builder.setTitle("Choose Croping App");
                builder.setCancelable(false);
                builder.setAdapter( adapter, new DialogInterface.OnClickListener() {
                    public void onClick( DialogInterface dialog, int item ) {
                        startActivityForResult( cropOptions.get(item).appIntent, CROPING_CODE);
                    }
                });

                builder.setOnCancelListener( new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel( DialogInterface dialog ) {

                        if (mImageCaptureUri != null ) {
                            getContentResolver().delete(mImageCaptureUri, null, null );
                            mImageCaptureUri = null;
                        }
                    }
                } );

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
            final int REQUIRED_SIZE = 512;
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
    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 40, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, 0);
        //set("encodedImage");
        return encodedImage;
    }
}


