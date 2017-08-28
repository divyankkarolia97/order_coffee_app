package com.example.android.justjava;

/**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 *
 */


import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

import static android.R.attr.hasCode;
import static android.R.attr.name;
import static android.R.attr.order;
import static android.R.attr.valueType;
import static java.security.AccessController.getContext;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
   // Resources res = this.getResources();
    int quantity = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v("MainActivity",""+quantity);

    }

    public void increment(View view){
        if(quantity ==100){
            Toast.makeText(this, "You cannot have more than 100 coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity++;
        display(quantity);

    }
    public void decrement(View view){
        if(quantity==1){
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();

            return;
        }
        quantity--;
        display(quantity);
    }



    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCreamBool = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        CheckBox chocolateBool = (CheckBox) findViewById(R.id.chocolate_checkbox);
        EditText nameView = (EditText) findViewById(R.id.name_view);
        String name = nameView.getText().toString();

        displaySummary(name,whippedCreamBool,chocolateBool,quantity*5);
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    /**
     * This method displays the given order summary on the screen.
     */
    private void displaySummary(String name,CheckBox whippedCreamBool,CheckBox chocolateBool, int number) {
        String orderSummary = "Name: "+name+"\nAdd Whipped Cream? "+whippedCreamBool.isChecked()+"\nAdd chocolate? "+chocolateBool.isChecked()+"\nQuantity: "+quantity+"\nTotal: $"+number+"\nThank You!";

        //intenting the final order summary
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "ORDER SUMMARY");
        intent.putExtra(Intent.EXTRA_TEXT,orderSummary);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}