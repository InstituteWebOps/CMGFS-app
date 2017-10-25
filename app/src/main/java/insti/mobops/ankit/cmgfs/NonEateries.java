package insti.mobops.ankit.cmgfs;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class NonEateries extends Fragment {

    private static final String QUESTION_URL = "https://students.iitm.ac.in/studentsapp/cmgfs/questionsneat.php";
    private static final String SUBMIT_URL = "https://students.iitm.ac.in/studentsapp/cmgfs/nonEateryEval.php";
    TextView date, shop, q1 , q2 ,q3, q4,q5, q6,q7,q8,q9, link;
    RatingBar rb2,rb3,rb4,rb5, rb6 , rb7, rb8;
    EditText members, phone ,com1,com2,com3,com4,com5,com6, com7, com8,com9;
    Switch s1;
    int sOne;
    Button submit;
    String shopName;
    ProgressDialog mProgress;
    String url = "https://www.facebook.com/CMGFSIITM";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.non_eateries, container, false);

        if (getArguments() != null) {
            shopName = getArguments().getString("FACY");
        }
        mProgress = new ProgressDialog(getActivity());
        mProgress.setMessage("Loading questionnaire..");
        mProgress.show();
        Question();
        date = (TextView)view.findViewById(R.id.cus_date);
        date.setText(giveDate());
        shop = (TextView)view.findViewById(R.id.cus_shop);
        shop.setText(shopName);
        members = (EditText)view.findViewById(R.id.members_name);
        phone = (EditText)view.findViewById(R.id.phone_no);
        q1 = (TextView)view.findViewById(R.id.cus_col1);
        q2 = (TextView)view.findViewById(R.id.cus_col2);
        q3 = (TextView)view.findViewById(R.id.cus_col3);
        q4 = (TextView)view.findViewById(R.id.cus_col4);
        q5 = (TextView)view.findViewById(R.id.cus_col5);
        q6 = (TextView)view.findViewById(R.id.cus_col6);
        q7 = (TextView)view.findViewById(R.id.cus_col7);
        q8 = (TextView)view.findViewById(R.id.cus_col8);
        q9 = (TextView)view.findViewById(R.id.cus_col9);

        link = (TextView)view.findViewById(R.id.fb);
        link.setPaintFlags(link.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });



        rb2 = (RatingBar)view.findViewById(R.id.ratingBar2);
        rb3 = (RatingBar)view.findViewById(R.id.ratingBar3);
        rb4 = (RatingBar)view.findViewById(R.id.ratingBar4);
        rb5 = (RatingBar)view.findViewById(R.id.ratingBar5);
        rb6 = (RatingBar)view.findViewById(R.id.ratingBar6);
        rb7 = (RatingBar)view.findViewById(R.id.ratingBar7);
        rb8 = (RatingBar)view.findViewById(R.id.ratingBar8);



        com1 = (EditText)view.findViewById(R.id.com1);
        com2 = (EditText)view.findViewById(R.id.com2);
        com3 = (EditText)view.findViewById(R.id.com3);
        com4 = (EditText)view.findViewById(R.id.com4);
        com5 = (EditText)view.findViewById(R.id.com5);
        com6 = (EditText)view.findViewById(R.id.com6);
        com7 = (EditText)view.findViewById(R.id.com7);
        com8= (EditText)view.findViewById(R.id.com8);
        com9 = (EditText)view.findViewById(R.id.com9);
        s1 = (Switch)view.findViewById(R.id.switch1);

        s1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    sOne = 1;
                }else{
                    sOne = 0;
                }
            }
        });
        submit = (Button)view.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();

            }
        });
        return view;
    }

    public void submit(){
        if ((com1.getText().toString() + com2.getText().toString() + com3.getText().toString() + com4.getText().toString() + com5.getText().toString() + com6.getText().toString() + com7.getText().toString() + com8.getText().toString() + com9.getText().toString()).equals("")) {
            Toast.makeText(getActivity(), "Empty fields!", Toast.LENGTH_SHORT).show();
            return;
        }
        final StringRequest stringStudAddRequest = new StringRequest(Request.Method.POST, SUBMIT_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(getActivity().getApplicationContext(),"Thanks for your review..", Toast.LENGTH_SHORT).show();

                // Check if no view has focus:
                View view = getActivity().getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                com1.setText("");
                com2.setText("");
                com3.setText("");
                com4.setText("");
                com5.setText("");
                com6.setText("");
                com7.setText("");
                com8.setText("");
                com9.setText("");
                members.setText("");
                phone.setText("");

                rb2.setRating(3);
                rb3.setRating(3);
                rb4.setRating(3);
                rb5.setRating(3);
                rb6.setRating(3);
                rb7.setRating(3);
                rb8.setRating(3);

                s1.setChecked(false);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                String message = null;
                if (error instanceof NetworkError) {
                    message = "Cannot connect to Internet. Please check your connection!!";
                } else if (error instanceof ServerError) {
                    message = "Server down. Please try again after some time!!";
                } else if (error instanceof AuthFailureError) {
                    message = "Authentication error!!";
                } else if (error instanceof ParseError) {
                    message = "Parsing error! Please try again after some time!!";
                } else if (error instanceof TimeoutError) {
                    message = "Connection TimeOut! Please check your internet connection.";
                }
                Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            }
        }) {



            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("member", members.getText().toString().trim());
                params.put("shop", shopName.trim());
                params.put("phone", phone.getText().toString().trim());
                params.put("1col", ""+sOne);
                params.put("2col", String.valueOf(rb2.getRating()));
                params.put("3col", String.valueOf(rb3.getRating()));
                params.put("4col", String.valueOf(rb4.getRating()));
                params.put("5col", String.valueOf(rb5.getRating()));
                params.put("6col", String.valueOf(rb6.getRating()));
                params.put("7col", String.valueOf(rb7.getRating()));
                params.put("8col", String.valueOf(rb8.getRating()));

                params.put("1comment", com1.getText().toString().trim());
                params.put("2comment", com2.getText().toString().trim());
                params.put("3comment", com3.getText().toString().trim());
                params.put("4comment", com4.getText().toString().trim());
                params.put("5comment", com5.getText().toString().trim());
                params.put("6comment", com6.getText().toString().trim());
                params.put("7comment", com7.getText().toString().trim());
                params.put("8comment", com8.getText().toString().trim());
                params.put("9comment", com9.getText().toString().trim());

                return params;
            }

        };


        MySingleton.getInstance(getActivity().getApplicationContext()).addToRequestQueue(stringStudAddRequest);

    }

    public void Question(){
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                QUESTION_URL, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                mProgress.dismiss();

                try {
                    // Parsing json object response
                    // response will be a json object
                    String ques1 = response.getString("1col");
                    String ques2 = response.getString("2col");
                    String ques3 = response.getString("3col");
                    String ques4 = response.getString("4col");
                    String ques5 = response.getString("5col");
                    String ques6 = response.getString("6col");
                    String ques7 = response.getString("7col");
                    String ques8 = response.getString("8col");
                    String ques9 = response.getString("9col");

                    q1.setText(ques1);
                    q2.setText(ques2);
                    q3.setText(ques3);
                    q4.setText(ques4);
                    q5.setText(ques5);
                    q6.setText(ques6);
                    q7.setText(ques7);
                    q8.setText(ques8);
                    q9.setText(ques9);




                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity().getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Eateries.class", "Error: " + error.getMessage());
                Toast.makeText(getActivity().getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                // hide the progress dialog

            }
        });
        MySingleton.getInstance(getActivity().getApplicationContext()).addToRequestQueue(jsonObjReq);
    }

    public String giveDate() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM d, yyyy");
        return sdf.format(cal.getTime());
    }
}