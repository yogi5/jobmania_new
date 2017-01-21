package com.youngindia.jobportal.database;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.HashMap;

/**
 * Created by anupam on 17-05-2016.
 */
public class SessionManager {
    public static final String KEY_VALUESERVER ="valueserver" ;
    public static final String KEY_TOKEN ="token" ;
    public static final String KEY_Highereducation1= "Highereducation1";
    public static final String KEY_Highereducation2= "Highereducation2";
    private static final String SET_DAILYFILTER ="SET_DAILYFILTER" ;
    // LogCat tag
    private static String TAG = SessionManager.class.getSimpleName();
    // Shared Preferences
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    public static final String SET_FILTER="filter";
    public static final String KEY_USERSEARCHKEYWORD="KEY_USERSEARCHKEYWORD";
    public static final String KEY_USERSEARCHQUALIFICATION="KEY_USERSEARCHQUALIFICATION";
    public static final String KEY_USERSEARCHLOCATION="KEY_USERSEARCHLOCATION";
    public static final String KEY_USERSEARCHKESALARYFIRST="KEY_USERSEARCHKESALARYFIRST";
    public static final String KEY_USERSEARCHKESALARSECOND="KEY_USERSEARCHKESALARSECOND";
    public static final String KEY_USERSEARCHEXP="KEY_USERSEARCHEXP";
    // Shared pref mode
    int PRIVATE_MODE = 0;
    // Shared preferences file name
    private static final String PREF_NAME = "Login";
    public static final String KEY_NAME = "username";
    public static final String KEY_NAME2 = "username1";
    public static final String KEY_COMPANYNAMENAME = "username";
    public static final String KEY_NAME1 = "name";
    public static final String KEY_MOBILE = "mobileno";
    public static final String KEY_STATUS = "status";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_DOB = "dob";
    public static final String KEY_LASTNAME = "lastname";
    public static final String KEY_GENDER = "gender";
    public static final String KEY_USERSTATUS = "userstatus";
    public static final String KEY_USERNAME1 = "username";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_CONFORMPASSWORD = "conformpassword";
    public static final String KEY_FRANCHISEVALUE = "franchisevalue";
    public static final String KEY_LOGIN = "loginstatus";
    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";
    public static final String KEY_CURRENTADDRESS = "current_address";
    public static final String KEY_CURRENTCITY = "currentcity";
    public static final String KEY_CURRENTPIN = "currentpincode";
    public static final String KEY_CURRENTSTATE = "currentstate";
    public static final String KEY_PERMANENTADDRESS = "permanent_address";
    public static final String KEY_PERMANENTCITY = "permanentcity";
    public static final String KEY_PERMANENTPIN = "permanentpincode";
    public static final String KEY_PERMANENTSTATE = "permanentstate";
    private static final String SET_FLAG = "flagvalue";
    private static final String SET_COMPANYFLAG = "flagvalue01";
    public static final String KEY_SKILL= "keyskills";
    public static final String KEY_LOCATIONPREFER="locationprefer";
    public static final String KEY_INDUSTRYPREFER="industryprefer";
    public static final String KEY_LOCATIONOTHER="locationothers";
    public static final String KEY_INDUSTRYOTHER="industryothers";
    public static final String KEY_CURRENTCOM="currentcom";
    public static final String KEY_CURRENTDES= "currentdes";
    public static final String KEY_CURRENTAUTO= "currentauto";
    public static final String KEY_EXPECTEDAUTO= "expectedauto";
    public static final String KEY_LOCATIONSPINNER= "locationspinner";
    public static final String KEY_INDUSRTYSPINNER= "industryspinner";
    public static final String KEY_CANDIDATESTATUS= "candidatestatus";
    public static final String KEY_Highereducation= "Highereducation";
    public static final String KEY_Highereducationuniversity= "Highereducationuniversity";
    public static final String KEY_higherEducationPercentage= "higherEducationPercentage";
    public static final String KEY_higherEducationYearPassing= "higherEducationYearPassing";
    public static final String KEY_secondry_percentage= "secondry_percentage";
    public static final String KEY_secondry_yearPassing= "secondry_yearPassing";
    public static final String KEY_tenth_percentage= "tenth_percentage";
    public static final String KEY_tenth_yearPassing= "tenth_yearPassing";
    public static final String KEY_otherEducation_name= "otherEducation_name";
    public static final String KEY_otherEducation_percentage= "otherEducation_percentage";
    public static final String KEY_otherEducation_yearPassing= "otherEducation_yearPassing";
    public static final String KEY_otherEducation_university= "otherEducation_university";
    public static final String SET_Otherlocality = "Otherlocality";
    public static final String KEY_LANGUAGEKNOWN= "languageknown";
    public static final String KEY_JOBPREF= "jobpref";
    public static final String KEY_CATEGORY="category";
    public static final String KEY_PHYCHAL="phychal";
    public static final String KEY_COMPLOC="companyloc";
    public static final String SET_Bachelerdegname= "Bachelerdegname";
    public static final String SET_Bachelerdeguniversity = "Bachelerdeguniversity ";
    public static final String SET_Bachelerdegyearofpassing = "Bachelerdegyearofpassing";
    public static final String SET_Bachelerdegpercentage = "Bachelerdegpercentage ";
    public static final String SET_Masterdegname= "Masterdegname";
    public static final String SET_Masterdeguniversity = "Masterdeguniversity ";
    public static final String SET_Masterdegyearofpassing = "Masterdegyearofpassing";
    public static final String  KEY_PERCENTAGEUPDATE = "percentage ";

    public static final String SET_Masterdegpercentage = "Masterdegpercentage ";
    public static final String KEY_HIGHEREDUCATION = "higherEducationName ";
    public static final String KEY_HIGHEREDUCATIONUniversity = "higherEducationNameuniversity ";
    public static final String KEY_HIGHEREDUCATIONPercetage = "KEY_HIGHEREDUCATIONPercetage ";
    public static final String KEY_HIGHEREDUCATIONyearofpassing = "KEY_HIGHEREDUCATIONyearofpassing ";
    public static final String SET_Otherlocalitystate = "SET_Otherlocalitystate ";


    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setLogin(boolean isLoggedIn) {
        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);
        // commit changes
        editor.commit();
        Log.d(TAG, "User login session modified!");
    }
    public void storeuser(String username){
        editor.putString(KEY_NAME, username);
        editor.commit();
    }
    public void storeusername(String username1){
        editor.putString(KEY_NAME2, username1);
        editor.commit();
    }
    public void storedetails(String name, String mobileno, String email,
                             String dob, String gender, String status, String username, String password,String conformpassword,
                             String loginstatus){

        editor.putString(KEY_NAME1, name);
        editor.putString(KEY_MOBILE, mobileno);
        editor.putString(KEY_STATUS, status);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_DOB, dob);
        editor.putString(KEY_GENDER, gender);

        editor.putString(KEY_USERNAME1, username);
        editor.putString(KEY_PASSWORD, password);
        editor.putString(KEY_CONFORMPASSWORD, conformpassword);

        editor.putString(KEY_LOGIN, loginstatus);
        editor.commit();
    }
    public HashMap<String, String> getUsername(){
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));
        // return user
        return user;
    }
    public HashMap<String, String> getUsername1(){
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(KEY_NAME2, pref.getString(KEY_NAME2, null));
        // return user
        return user;
    }
    public void storecompanyname(String companyname){
        editor.putString(KEY_COMPANYNAMENAME, companyname);
        editor.commit();
    }
    public HashMap<String, String> geteducationname(){
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(KEY_HIGHEREDUCATION, pref.getString(KEY_HIGHEREDUCATION, null));
        user.put(KEY_HIGHEREDUCATIONUniversity, pref.getString(KEY_HIGHEREDUCATIONUniversity, null));
        user.put(KEY_HIGHEREDUCATIONPercetage, pref.getString(KEY_HIGHEREDUCATIONPercetage, null));
        user.put(KEY_HIGHEREDUCATIONyearofpassing, pref.getString(KEY_HIGHEREDUCATIONyearofpassing, null));
        // return user
        return user;
    }
    public void seteducation(String higherEducationName, String higherEducationUniversity, String higherEducationPercentage, String higherEducationYearPassing){
        editor.putString(KEY_HIGHEREDUCATION, higherEducationName);
        editor.putString(KEY_HIGHEREDUCATIONUniversity, higherEducationUniversity);
        editor.putString(KEY_HIGHEREDUCATIONPercetage, higherEducationPercentage);
        editor.putString(KEY_HIGHEREDUCATIONyearofpassing, higherEducationYearPassing);
        editor.commit();
    }
    public HashMap<String, String> getcompanyname(){
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(KEY_COMPANYNAMENAME, pref.getString(KEY_COMPANYNAMENAME, null));
        // return user
        return user;
    }
    public HashMap<String, String> getCompanylocation(){
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(KEY_COMPLOC, pref.getString(KEY_COMPLOC, null));
        // return user
        return user;
    }
    public void storecompanyloc(String location){
        editor.putString(KEY_COMPLOC, location);
        editor.commit();
    }
    public void setpercentage(int percentage){
        editor.putInt(KEY_PERCENTAGEUPDATE, percentage);
        editor.commit();
    }
    public HashMap<String, Integer> getpercentage(){
        HashMap<String, Integer> user = new HashMap<>();
        // user name
        user.put(KEY_PERCENTAGEUPDATE, pref.getInt(KEY_PERCENTAGEUPDATE, 0));
        // return user
        return user;
    }


    public HashMap<String, String> getstoredetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(KEY_NAME1, pref.getString(KEY_NAME1, null));
        user.put(KEY_MOBILE, pref.getString(KEY_MOBILE, null));
        user.put(KEY_STATUS, pref.getString(KEY_STATUS, null));
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));
        user.put(KEY_DOB, pref.getString(KEY_DOB, null));
        user.put(KEY_GENDER, pref.getString(KEY_GENDER, null));

        user.put(KEY_USERNAME1, pref.getString(KEY_USERNAME1, null));
        user.put(KEY_PASSWORD, pref.getString(KEY_PASSWORD, null));
        user.put(KEY_CONFORMPASSWORD, pref.getString(KEY_CONFORMPASSWORD, null));

        user.put(KEY_LOGIN, pref.getString(KEY_LOGIN, null));// return user
        return user;
    }


    public boolean isLoggedIn(){
        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }
    public boolean getflag(){
        return pref.getBoolean(SET_FLAG, false);
    }

    public void setflag(boolean flagvalue1) {
        editor.putBoolean(SET_FLAG, flagvalue1);
        editor.commit();
    }

    public boolean getcompanyflag(){
        return pref.getBoolean(SET_COMPANYFLAG, false);
    }

    public void setcompanyflag(boolean flagvalue1) {
        editor.putBoolean(SET_COMPANYFLAG, flagvalue1);
        editor.commit();
    }
    public void storeaddress(String current_address, String currentcity, String currentpincode, String currentstate,
                             String permanent_address, String permanentcity, String permanentpincode, String permanentstate) {

        editor.putString(KEY_CURRENTADDRESS, current_address);
        editor.putString(KEY_CURRENTCITY, currentcity);
        editor.putString(KEY_CURRENTPIN, currentpincode);
        editor.putString(KEY_CURRENTSTATE, currentstate);
        editor.putString(KEY_PERMANENTADDRESS, permanent_address);
        editor.putString(KEY_PERMANENTCITY, permanentcity);
        editor.putString(KEY_PERMANENTPIN, permanentpincode);
        editor.putString(KEY_PERMANENTSTATE, permanentstate);
        editor.commit();
    }

    public HashMap<String, String> getstoreaddress(){
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(KEY_CURRENTADDRESS, pref.getString(KEY_CURRENTADDRESS, null));
        user.put(KEY_CURRENTCITY, pref.getString(KEY_CURRENTCITY, null));
        user.put(KEY_CURRENTPIN, pref.getString(KEY_CURRENTPIN, null));
        user.put(KEY_CURRENTSTATE, pref.getString(KEY_CURRENTSTATE, null));
        user.put(KEY_PERMANENTADDRESS, pref.getString(KEY_PERMANENTADDRESS, null));
        user.put(KEY_PERMANENTCITY, pref.getString(KEY_PERMANENTCITY, null));
        user.put(KEY_PERMANENTPIN, pref.getString(KEY_PERMANENTPIN, null));
        user.put(KEY_PERMANENTSTATE, pref.getString(KEY_PERMANENTSTATE, null));
        // return user
        return user;
    }

    public HashMap<String, String> getstorekeydetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(KEY_SKILL, pref.getString(KEY_SKILL, null));
        user.put(KEY_LOCATIONPREFER, pref.getString(KEY_LOCATIONPREFER, null));
        user.put(KEY_INDUSTRYPREFER, pref.getString(KEY_INDUSTRYPREFER, null));
        user.put(KEY_LOCATIONOTHER, pref.getString(KEY_LOCATIONOTHER, null));
        user.put(KEY_INDUSTRYOTHER, pref.getString(KEY_INDUSTRYOTHER, null));
        user.put(KEY_CURRENTCOM, pref.getString(KEY_CURRENTCOM, null));
        user.put(KEY_CURRENTDES, pref.getString(KEY_CURRENTDES, null));
        user.put(KEY_CURRENTAUTO, pref.getString(KEY_CURRENTAUTO, null));
        user.put(KEY_EXPECTEDAUTO, pref.getString(KEY_EXPECTEDAUTO, null));
        user.put(KEY_LOCATIONSPINNER, pref.getString(KEY_LOCATIONSPINNER, null));
        user.put(KEY_INDUSRTYSPINNER, pref.getString(KEY_INDUSRTYSPINNER, null));
        user.put(KEY_CANDIDATESTATUS, pref.getString(KEY_CANDIDATESTATUS, null));
        // return user
        return user;
    }

    public void storekeydetails(String keyskills,String industryprefer,
                                String locationothers, String industryothers, String currentcom, String currentdes,
                                String currentauto, String expectedauto, String locationspinnervalue, String industryspinnervalue, String candidatestatus){

        editor.putString(KEY_SKILL, keyskills);

        editor.putString(KEY_INDUSTRYPREFER, industryprefer);
        editor.putString(KEY_LOCATIONOTHER, locationothers);
        editor.putString(KEY_INDUSTRYOTHER, industryothers);
        editor.putString(KEY_CURRENTCOM, currentcom);
        editor.putString(KEY_CURRENTDES, currentdes);
        editor.putString(KEY_CURRENTAUTO, currentauto);
        editor.putString(KEY_EXPECTEDAUTO, expectedauto);
        editor.putString(KEY_LOCATIONSPINNER, locationspinnervalue);
        editor.putString(KEY_INDUSRTYSPINNER, industryspinnervalue);
        editor.putString(KEY_CANDIDATESTATUS, candidatestatus);
        editor.commit();
    }


    public void storeeducationadetails( String secondry_percentage, String secondry_yearPassing,
                                        String tenth_percentage, String tenth_yearPassing, String otherEducation_name, String otherEducation_percentage,
                                        String otherEducation_yearPassing, String otherEducation_university) {
        editor.putString(KEY_secondry_percentage, secondry_percentage);
        editor.putString(KEY_secondry_yearPassing, secondry_yearPassing);
        editor.putString(KEY_tenth_percentage, tenth_percentage);
        editor.putString(KEY_tenth_yearPassing, tenth_yearPassing);
        editor.putString(KEY_otherEducation_name, otherEducation_name);
        editor.putString(KEY_otherEducation_percentage, otherEducation_percentage);
        editor.putString(KEY_otherEducation_yearPassing, otherEducation_yearPassing);
        editor.putString(KEY_otherEducation_university, otherEducation_university);
        editor.commit();
    }


    public HashMap<String, String> getstoreeducationadetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(KEY_secondry_percentage, pref.getString(KEY_secondry_percentage, null));
        user.put(KEY_secondry_yearPassing, pref.getString(KEY_secondry_yearPassing, null));
        user.put(KEY_tenth_percentage, pref.getString(KEY_tenth_percentage, null));
        user.put(KEY_tenth_yearPassing, pref.getString(KEY_tenth_yearPassing, null));
        user.put(KEY_otherEducation_name, pref.getString(KEY_otherEducation_name, null));
        user.put(KEY_otherEducation_percentage, pref.getString(KEY_otherEducation_percentage, null));
        user.put(KEY_otherEducation_yearPassing, pref.getString(KEY_otherEducation_yearPassing, null));
        user.put(KEY_otherEducation_university, pref.getString(KEY_otherEducation_university, null));
        // return user
        return user;
    }

    public void setbachelorqualificationdegree(String bachelordegreename,String bachelordegreeuniversity,String yearofpassing,String percentage ) {
        editor.putString(SET_Bachelerdegname, bachelordegreename);
        editor.putString(SET_Bachelerdeguniversity, bachelordegreeuniversity);
        editor.putString(SET_Bachelerdegyearofpassing, yearofpassing);
        editor.putString(SET_Bachelerdegpercentage, percentage);
        editor.commit();
    }
    public HashMap<String, String> getbachelorqualificationdegree() {
        HashMap<String, String> userbachelorvalue = new HashMap<String, String>();
        userbachelorvalue.put(SET_Bachelerdegname, pref.getString(SET_Bachelerdegname, null));
        userbachelorvalue.put(SET_Bachelerdeguniversity, pref.getString(SET_Bachelerdeguniversity, null));
        userbachelorvalue.put(SET_Bachelerdegyearofpassing, pref.getString(SET_Bachelerdegyearofpassing, null));
        userbachelorvalue.put(SET_Bachelerdegpercentage, pref.getString(SET_Bachelerdegpercentage, null));
        return userbachelorvalue;
    }

    public void setmasterqualificationdegree(String masterdegreename,String masterdegreeuniversity,String masteryearofpassing,String masterpercentage ) {
        editor.putString(SET_Masterdegname, masterdegreename);
        editor.putString(SET_Masterdeguniversity, masterdegreeuniversity);
        editor.putString(SET_Masterdegyearofpassing, masteryearofpassing);
        editor.putString(SET_Masterdegpercentage, masterpercentage);
        editor.commit();
    }
    public HashMap<String, String> getmasterqualificationdegree() {
        HashMap<String, String> userbachelorvalue = new HashMap<String, String>();
        userbachelorvalue.put(SET_Masterdegname, pref.getString(SET_Masterdegname, null));
        userbachelorvalue.put(SET_Masterdeguniversity, pref.getString(SET_Masterdeguniversity, null));
        userbachelorvalue.put(SET_Masterdegyearofpassing, pref.getString(SET_Masterdegyearofpassing, null));
        userbachelorvalue.put(SET_Masterdegpercentage, pref.getString(SET_Masterdegpercentage, null));
        return userbachelorvalue;
    }
    public HashMap<String, String> getotherlocality() {
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(SET_Otherlocality, pref.getString(SET_Otherlocality, null));
        user.put(SET_Otherlocalitystate, pref.getString(SET_Otherlocalitystate, null));
        // return user
        return user;
    }
    public void setotherlocality(String flagvalue1, String state) {
        editor.putString(SET_Otherlocality, flagvalue1);
        editor.putString(SET_Otherlocalitystate, state);
        editor.commit();
    }
    public void storelanguageknown(String languageknown, String jobpref, String category, String phychal) {
        editor.putString(KEY_LANGUAGEKNOWN, languageknown);
        editor.putString(KEY_JOBPREF, jobpref);
        editor.putString(KEY_CATEGORY,category);
        editor.putString(KEY_PHYCHAL, phychal);

        editor.commit();
    }

    public HashMap<String,String> getstorelanguageknown() {
        HashMap<String, String> user = new HashMap<String, String>();
        // user name


        user.put(KEY_LANGUAGEKNOWN, pref.getString(KEY_LANGUAGEKNOWN, null));
        user.put(KEY_JOBPREF, pref.getString(KEY_JOBPREF, null));
        user.put(KEY_CATEGORY, pref.getString(KEY_CATEGORY, null));
        user.put(KEY_PHYCHAL, pref.getString(KEY_PHYCHAL, null));



        // return user
        return user;
    }

    public void storevalue(String valueserver) {
        editor.putString(KEY_VALUESERVER,valueserver);
        editor.commit();
    }
    public HashMap<String,String> getserverdetails() {
        HashMap<String, String> user2 = new HashMap<String, String>();
        // user name


        user2.put(KEY_VALUESERVER, pref.getString(KEY_VALUESERVER, null));




        // return user
        return user2;
    }

    public void storetoken(String token) {
        editor.putString(KEY_TOKEN,token);
        editor.commit();
    }

    public HashMap<String,String> gettoken() {
        HashMap<String, String> user2 = new HashMap<String, String>();
        // user name


        user2.put(KEY_TOKEN, pref.getString(KEY_TOKEN, null));




        // return user
        return user2;
    }

    public void storeedu(String highereducation) {
        editor.putString(KEY_Highereducation1, highereducation);
        //editor.putString(KEY_Highereducation2, highereducation1);
        editor.commit();
    }

    public HashMap<String,String> getstoreedu() {
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(KEY_Highereducation1, pref.getString(KEY_Highereducation1, null));
        //user.put(KEY_Highereducation2, pref.getString(KEY_Highereducation2, null));
        // return user
        return user;
    }
    public void storeusersearchvalue(String keyword1, String qualification1, String location1, String salaryfirst,
                                     String salarysecond, String experiencevalue) {
        editor.putString(KEY_USERSEARCHKEYWORD, keyword1);
        editor.putString(KEY_USERSEARCHQUALIFICATION, qualification1);
        editor.putString(KEY_USERSEARCHLOCATION,location1);
        editor.putString(KEY_USERSEARCHKESALARYFIRST, salaryfirst);
        editor.putString(KEY_USERSEARCHKESALARSECOND,salarysecond);
        editor.putString(KEY_USERSEARCHEXP, experiencevalue);
        editor.commit();

    }

    public HashMap<String,String> getusersearchvalue() {
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(KEY_USERSEARCHKEYWORD, pref.getString(KEY_USERSEARCHKEYWORD, null));
        user.put(KEY_USERSEARCHQUALIFICATION, pref.getString(KEY_USERSEARCHQUALIFICATION, null));
        user.put(KEY_USERSEARCHLOCATION, pref.getString(KEY_USERSEARCHLOCATION, null));
        user.put(KEY_USERSEARCHKESALARYFIRST, pref.getString(KEY_USERSEARCHKESALARYFIRST, null));
        user.put(KEY_USERSEARCHKESALARSECOND, pref.getString(KEY_USERSEARCHKESALARSECOND, null));
        user.put(KEY_USERSEARCHEXP, pref.getString(KEY_USERSEARCHEXP, null));
        // return user
        return user;
    }

    public boolean getfilter(){
        return pref.getBoolean(SET_FILTER, false);
    }

    public void setfilter(boolean flagvalue1) {
        editor.putBoolean(SET_FILTER, flagvalue1);
        editor.commit();
    }

    public void setdailyfilter(boolean b) {
        editor.putBoolean(SET_DAILYFILTER, b);
        editor.commit();
    }
    public boolean getdailyfilter(){
        return pref.getBoolean(SET_DAILYFILTER, false);
    }
}