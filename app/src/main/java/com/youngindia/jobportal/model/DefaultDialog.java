package com.youngindia.jobportal.model;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.youngindia.jobportal.R;
import com.youngindia.jobportal.ui.LoginUser_Activity;

import javax.mail.Session;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageInstaller;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 * Created by anupam on 23-05-2016.
 */
public class DefaultDialog extends  Activity{
    Session session = null;
    EditText receip;
    String rec, subject, textMessage;
    TextView outputemail;

//    DefaultDialog(Context context)
//    {
//        this.context=context;
//    }

    public void showDialog(final Activity activity, String msg) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialogbox);

        //context = this;

        TextView text = (TextView) dialog.findViewById(R.id.txt_header);
        text.setText(msg);
        receip = (EditText)dialog.findViewById(R.id.edittexttoresetpassword);
        final Button dialogButton = (Button) dialog.findViewById(R.id.btn_no);
        outputemail=(TextView)dialog.findViewById(R.id.outputemailtext);
        final Button buttonback=(Button)dialog.findViewById(R.id.backbutton);
        dialog.show();



        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(,"A link to reset your Jobby Password has been sent to your Email Id",Toast.LENGTH_SHORT).show();
                //dialog.dismiss();
                if(receip.length()==0)
                {
//                    Toast.makeText(getApplicationContext(),"Please Enter Email",Toast.LENGTH_SHORT).show();
                    outputemail.setText("Please Enter the Email");
                }else{
                rec = receip.getText().toString();
                    if(rec.contains("@")) {
                        String method="emailcheck";
                        BackgroundWorker backgroundTask = new BackgroundWorker(DefaultDialog.this);
                        backgroundTask.execute(method, rec);

                       /*public  void emailsend()
                    {
                        //dialog.dismiss();
                        // isEmailValid(rec);
                        subject = "Reset Your Password";
                        textMessage = "Dear User   \n       Click below link to reset your password   \n         http://youngindiagroup.in/jobportal/reset.php    \n      Best Regards,\n Jobby.com";

                        Properties props = new Properties();
                        props.put("mail.smtp.host", "smtp.gmail.com");
                        props.put("mail.smtp.socketFactory.port", "465");
                        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                        props.put("mail.smtp.auth", "true");
                        props.put("mail.smtp.port", "465");


                        session = Session.getDefaultInstance(props, new Authenticator() {
                            @Override
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication("jobbybangalore@gmail.com", "youngindia");
                            }
                        });

//                          ProgressDialog   pdialog = ProgressDialog.show(getApplicationContext(), "", "Sending Mail....", true);
//                        pdialog.show();
                        RetrieveFeedTask task = new RetrieveFeedTask(DefaultDialog.this);
                        task.execute();


                        //             dialog.dismiss();
                        outputemail.setText("Email has been sent successfully........check your Email");

//                LoginUser_Activity abc=new LoginUser_Activity();
//                abc.ToastMethod();
//               Toast.makeText(DefaultDialog.this, "Message Sent", Toast.LENGTH_LONG).show();
                    }*/
//
                }else
                    {
                        outputemail.setText("Email is not Valid. Please enter valid Email");
                    }

            }}

            /*public void emailsend()
            {
                //dialog.dismiss();
                // isEmailValid(rec);
                subject = "Reset Your Password";
                textMessage = "Dear User   \n       Click below link to reset your password   \n         http://youngindiagroup.in/jobportal/reset.php    \n      Best Regards,\n Jobby.com";

                Properties props = new Properties();
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.socketFactory.port", "465");
                props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.port", "465");


                session = Session.getDefaultInstance(props, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("jobbybangalore@gmail.com", "youngindia");
                    }
                });

//                          ProgressDialog   pdialog = ProgressDialog.show(getApplicationContext(), "", "Sending Mail....", true);
//                        pdialog.show();
                RetrieveFeedTask task = new RetrieveFeedTask(DefaultDialog.this);
                task.execute();


                //             dialog.dismiss();
                outputemail.setText("Email has been sent successfully........check your Email");

//                LoginUser_Activity abc=new LoginUser_Activity();
//                abc.ToastMethod();
//               Toast.makeText(DefaultDialog.this, "Message Sent", Toast.LENGTH_LONG).show();
            }*/
//            private boolean isEmailValid(String email) {
//                //TODO: Replace this with your own logic
//                return email.contains("@");
//            }

        });
        //    Button dialogButton1 = (Button) dialog.findViewById(R.id.btn_yes);
        buttonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.hide();
            }
        });


     //   dialog.show();
//        Toast.makeText(getApplicationContext(), "Message Sent", Toast.LENGTH_LONG).show();

    }


    class RetrieveFeedTask extends AsyncTask<String, Void, String> {

        ProgressDialog pdialog=null;
        Context context=null;
        RetrieveFeedTask(Context context)
    {
        this.context=context;
    }





        @Override
        protected String doInBackground(String... params) {

            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("jobbybangalore@gmail.com"));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(rec));
                message.setSubject(subject);
                message.setContent(textMessage, "text/html; charset=utf-8");

                Transport.send(message);
                pdialog = ProgressDialog.show(getApplicationContext(), "", "Sending Mail....", true);
            } catch (MessagingException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
//            pdialog.dismiss();
            //result="Email Sent";
            //super.onPostExecute(result);
            receip.setText("");

            //                 msg.setText("");
            //                 sub.setText("");
        //    Toast.makeText(getApplicationContext(),result, Toast.LENGTH_LONG).show();
        }
    }



    //import static android.support.v4.app.ActivityCompat.startActivity;

    /**
     * Created by YOGI on 6/5/2016.
     */
     class BackgroundWorker extends AsyncTask<String,Void,String> {
        Context context;
        ProgressDialog pDialog;

        BackgroundWorker(Context ctx) {
            context = ctx;
        }

        @Override
        protected void onPreExecute() {
        //    alertDialog = new AlertDialog.Builder(context).create();
          //  alertDialog.setTitle("Login Status");

        }

        @Override
        protected String doInBackground(String... params) {
            String type = params[0];
            String email_url = "http://youngindiagroup.in/jobportal/emailcheck.php" ;
           // String register_url = "http://10.0.2.2:8080/hotelregister.php";
            if (type.equals("method")) {
                try {
                    String user_name = params[1];
                    //String password = params[2];
                    URL url = new URL(email_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(user_name, "UTF-8");
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                    String result = "";
                    String line = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        result += line;
                    }

                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return result;



                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            /*else if (type.equals("register")) {
                try {
                    String username = params[1];
                    String password = params[2];
                    String reenterpassword = params[3];
                    String mobile = params[4];
                    String email = params[5];
                    URL url = new URL(register_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&"
                            + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8") + "&"
                            + URLEncoder.encode("reenterpassword", "UTF-8") + "=" + URLEncoder.encode(reenterpassword, "UTF-8") + "&"
                            + URLEncoder.encode("mobile", "UTF-8") + "=" + URLEncoder.encode(mobile, "UTF-8") + "&"
                            + URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                    String result = "";
                    String line = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        result += line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return result;
*/
//                }
  /*      catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
  *///              }
      //      }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
//            dialog.setMessage(result);
  //          alertDialog.show();

//            if(result.equals("email already exists"))
//            {
//                pDialog = ProgressDialog.show(getApplicationContext(), "", "Email already exists....", true);
//                Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(context, IceCreams.class);
//                context.startActivity(intent);
                //alertDialog.dismiss();
                //((Activity)context).finish();
//            }
//            if(result.equals("entered email doesnot exist"))
//            {
              //  pDialog = ProgressDialog.show(getApplicationContext(), "", "Email is not valid....", true);
//                Intent intent = new Intent(context, IceCreams.class);
//                context.startActivity(intent);
                //alertDialog.dismiss();
              //  Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_LONG).show();


                // ((Activity)context).finish();
            //}
//            if(result.equals("connection success....login not success")){
//                alertDialog.setMessage("Login Failed....");
////            Intent intent = new Intent(context, .class);
////            context.startActivity(intent);
//                //alertDialog.dismiss();
//                // Thread.interrupted();
//
//            }
//            //startActivity(new Intent(this, HomeActivity.class));


        }



        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }


}
