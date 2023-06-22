package com.example.duan1_ph27232;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.duan1_ph27232.fragment.DoiPassFragment;
import com.example.duan1_ph27232.fragment.TrangChuFragment;
import com.example.duan1_ph27232.fragment.qlDanhMucFragment;
import com.example.duan1_ph27232.fragment.qlDienThoaiFragment;
import com.example.duan1_ph27232.fragment.qlDoanhThuFragment;
import com.example.duan1_ph27232.fragment.qlHoaDonFragment;
import com.example.duan1_ph27232.fragment.qlKhachHangFragment;
import com.example.duan1_ph27232.fragment.qlKhuyenMaiFragment;
import com.example.duan1_ph27232.fragment.qlNhanVienFragment;
import com.example.duan1_ph27232.fragment.qlTop10Fragment;
import com.example.duan1_ph27232.model.Admin;
import com.example.duan1_ph27232.model.NhanVien;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Admin admin;
    NavigationView navigationView;
    NhanVien nhanVien=new NhanVien();
    TextView welcome;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolBar);
         navigationView = findViewById(R.id.navigationView);
        drawerLayout = findViewById(R.id.drawerLayout);
        admin=new Admin();
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("");
replaceFragment(new TrangChuFragment());
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment =null;
                switch (item.getItemId()){
                    case R.id.mtrangchu:
                    fragment=new TrangChuFragment();
                        break;
                    case R.id.mQLNhanVien:
                     startActivity(new Intent(MainActivity.this,qlNhanVienFragment.class));
                        break;
                    case R.id.mQLDienThoai:
                        startActivity(new Intent(MainActivity.this,qlDienThoaiFragment.class));
                        break;
                    case R.id.mQLKhachHang:
                       startActivity(new Intent(MainActivity.this,qlKhachHangFragment.class));
                        break;
                    case R.id.mQLDanhMuc:n:
                       startActivity( new Intent(MainActivity.this,qlDanhMucFragment.class));
                        break;
                    case R.id.mQLKhuyenMai:n:
                    startActivity(new Intent(MainActivity.this,qlKhuyenMaiFragment.class));
                        break;
                    case R.id.mQLHoaDon:n:
                    startActivity(new Intent(MainActivity.this,qlHoaDonFragment.class));
                        break;
                    case R.id.mThoat:
                        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("EXIST");
                        builder.setMessage("Bạn có muốn thoát không ?");
                        builder.setCancelable(true);
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        AlertDialog alertDialog=builder.create();
                        alertDialog.show();

                        break;
                    case R.id.mdoiMK:
                startActivity(new Intent(MainActivity.this,DoiPassFragment.class));
                        break;
                    case R.id.mTop10:
                        startActivity(new Intent(MainActivity.this,qlTop10Fragment.class));
                        break;
                    case R.id.mDoanhThu:
                        startActivity(new Intent(MainActivity.this,qlDoanhThuFragment.class));
                        break;
                    default:
                        startActivity(new Intent(MainActivity.this,qlDienThoaiFragment.class));
                        break;
                }
                if(fragment != null){
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit();
                    toolbar.setTitle(item.getTitle());
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
}

    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout,fragment);
        transaction.commit();
    }
public void showinfor(){
        int id=LoginActivity.id;
    Menu menu = navigationView.getMenu();
        if(id!=1){

            menu.findItem(R.id.mQLNhanVien).setVisible(false);
            menu.findItem(R.id.mdoiMK).setVisible(false);
        }
        else {
            menu.findItem(R.id.mQLNhanVien).setVisible(true);
            menu.findItem(R.id.mdoiMK).setVisible(true);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        showinfor();
    }

}