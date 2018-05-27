package fr.musee.adr.adrmusee;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class CompteActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    public static Basket userbasket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compte);
    loadFragment(new AccueilFragment());
    userbasket = new Basket();
    BottomNavigationView menu_nav = findViewById(R.id.Navbot);
        menu_nav.setOnNavigationItemSelectedListener(this);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.navigation_accueil:
                fragment = new AccueilFragment();
                break;

            case R.id.navigation_panier:
                fragment = new PanierFragment();
                break;
            case R.id.navigation_commandes:
                fragment = new CommandesFragment();
                break;

            case R.id.navigation_profil:
                fragment = new ProfilFragment();
                break;

        }

        return loadFragment(fragment);
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}