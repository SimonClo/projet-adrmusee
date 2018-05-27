package fr.musee.adr.adrmusee;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class AdminFragment extends Fragment {
    private DatabaseReference mDatabaseUsers;
    private ArrayList list_users;
    private ArrayList list_emails;
    private ArrayList list_isadmin;
    private Button AdminButton;
    private Button RemoveAdminButton;

    private DatabaseReference mDatabaseProducts;
    private ArrayList list_products;
    private ArrayList list_id_products;
    private Button RemoveProductButton;
    private Button AddProductButton;
    public static Basket userbasket;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_admin_fragment, null);
        userbasket = new Basket();
        list_users = new ArrayList<>();
        list_emails = new ArrayList<>();
        list_isadmin = new ArrayList<>();
        mDatabaseUsers = FirebaseDatabase.getInstance().getReference().child("Users");
        mDatabaseUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String user_id = ds.getKey().toString();
                    String email = ds.child("email").getValue().toString();
                    long isadmin = Long.parseLong(ds.child("isadmin").getValue().toString());
                    list_users.add(user_id);
                    list_emails.add(email);
                    list_isadmin.add(isadmin);

                }
                AdminButton = (Button) view.findViewById(R.id.ButtonAdmin);
                RemoveAdminButton = (Button) view.findViewById(R.id.RemoveButtonAdmin);
                final Spinner SpinnerUsers = (Spinner) view.findViewById(R.id.SpinnerUsers);
                ArrayAdapter<String> dataAdapterR = new ArrayAdapter<String>(AdminFragment.this.getActivity(), android.R.layout.simple_spinner_item, list_emails);
                dataAdapterR.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                SpinnerUsers.setAdapter(dataAdapterR);

                SpinnerUsers.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        final String Email = String.valueOf(SpinnerUsers.getSelectedItem());
                        Integer positionItem = (Integer) SpinnerUsers.getSelectedItemPosition();
                        final String user_id = list_users.get(positionItem).toString();
                        long isadmin = Long.parseLong(list_isadmin.get(positionItem).toString());
                        if(isadmin==1){
                        Toast.makeText(getActivity(),Email + " est admin", Toast.LENGTH_SHORT).show();}else{
                            Toast.makeText(getActivity(),Email + " n'est pas admin", Toast.LENGTH_SHORT).show();
                        }
                        AdminButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mDatabaseUsers.child(user_id).child("isadmin").setValue(1L);
                                list_emails.clear();
                                list_users.clear();
                                list_isadmin.clear();
                                Toast.makeText(getActivity(),Email + " est maintenant admin", Toast.LENGTH_SHORT).show();
                            }
                        });
                        RemoveAdminButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mDatabaseUsers.child(user_id).child("isadmin").setValue(0L);
                                list_emails.clear();
                                list_users.clear();
                                list_isadmin.clear();
                                Toast.makeText(getActivity(),Email + " n'est plus admin", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mDatabaseProducts = FirebaseDatabase.getInstance().getReference().child("Product");
        list_products = new ArrayList<>();
        list_id_products = new ArrayList<>();
        mDatabaseProducts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String product_id = ds.getKey().toString();
                    String name = ds.child("name").getValue().toString();
                    list_id_products.add(product_id);
                    list_products.add(name);
                }
                RemoveProductButton = (Button) view.findViewById(R.id.RemoveProduct);
                final Spinner SpinnerProducts = (Spinner) view.findViewById(R.id.SpinnerProducts);
                ArrayAdapter<String> dataAdapterR = new ArrayAdapter<String>(AdminFragment.this.getActivity(), android.R.layout.simple_spinner_item, list_products);
                dataAdapterR.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                SpinnerProducts.setAdapter(dataAdapterR);

                SpinnerProducts.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        final String name = String.valueOf(SpinnerProducts.getSelectedItem());
                        Integer positionItem = (Integer) SpinnerProducts.getSelectedItemPosition();
                        final String product_id = list_id_products.get(positionItem).toString();
                        RemoveProductButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mDatabaseProducts.child(product_id).removeValue();
                                list_products.clear();
                                list_id_products.clear();
                                Toast.makeText(getActivity(),name + " a été retiré de la liste des produits", Toast.LENGTH_SHORT).show();

                            }
                        });

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        AddProductButton = (Button) view.findViewById(R.id.AddProduct);
        AddProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayDialog();

            }
        });


        return view;

    }

    final static String DB_URL = "https://adrmusee.firebaseio.com/Product";
    FirebaseClient firebaseClient;
    EditText nameeditText, urleditText, priceEdit;
    Button btnsave,btncancel;
    ListView listView;

    private void displayDialog() {
        final Dialog d = new Dialog(this.getActivity());
        d.setTitle("SaveData");
        d.setContentView(R.layout.custom_dialog);
        listView = (ListView) d.findViewById(R.id.list_products);
        firebaseClient = new FirebaseClient(this.getActivity(), DB_URL, listView);
        nameeditText = (EditText) d.findViewById(R.id.nameEditText);
        urleditText = (EditText) d.findViewById(R.id.urlEditText);
        priceEdit = (EditText) d.findViewById(R.id.priceEdit);

        if (nameeditText.getText().toString() != "" && priceEdit.getText().toString() != "") {

            btnsave = (Button) d.findViewById(R.id.saveBtn);
            btncancel = (Button) d.findViewById(R.id.cancelBtn);
            btnsave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (nameeditText.getText().toString() != "" && priceEdit.getText().toString() != "") {
                        firebaseClient.savedata(nameeditText.getText().toString(), Float.parseFloat(priceEdit.getText().toString()), urleditText.getText().toString());
                        nameeditText.setText("");
                        urleditText.setText("");
                        priceEdit.setText("");
                        d.dismiss();
                        Toast.makeText(getActivity(), "Votre produit a été rajouté", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "Veuillez renseigner le nom et le prix", Toast.LENGTH_SHORT).show();
                    }

                }
            });
            btncancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    d.dismiss();
                }
            });

            d.show();
        }

    }
}

