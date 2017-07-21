package lsw.newboost3.Activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import lsw.newboost3.Fragment.InputDataFragment;
import lsw.newboost3.Fragment.mapFragment;
import lsw.newboost3.ItemResource.Item;
import lsw.newboost3.R;

public class MainActivity extends AppCompatActivity implements InputDataFragment.OnOpenMapListener, InputDataFragment.DatabaseListener{
    private FirebaseDatabase database= FirebaseDatabase.getInstance();
    private DatabaseReference resRef= database.getReference();
    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar) ;
        toolbar.setTitle("맛집 등록");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        final InputDataFragment InputFragment = new InputDataFragment();

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment, InputFragment);
        fragmentTransaction.commit();


    }
    public void onOpenMap(Item item){
        mapFragment map2Fragment = mapFragment.newInstance(item);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment, map2Fragment );
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();


    }

    public void putData(Item item){


        resRef.child("resData").push().setValue(item);

    }


}
