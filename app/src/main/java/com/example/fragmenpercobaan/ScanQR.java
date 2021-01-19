//package com.example.fragmenpercobaan;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.viewpager.widget.ViewPager;
//
//import android.os.Bundle;
//
//import com.google.android.material.tabs.TabLayout;
//import com.kishan.askpermission.ErrorCallback;
//import com.kishan.askpermission.PermissionCallback;
//
//public class ScanQR extends AppCompatActivity implements PermissionCallback, ErrorCallback {
//    private static final int REQUEST_PERMISSIONS = 20;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_scan_q_r);
//
//        if (getSupportActionBar() != null) {
//            getSupportActionBar().setTitle("Presensi Peserta");
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        }
//        SectionPagerScanAdapter sectionPagerAdapter = new SectionPagerScanAdapter(getSupportFragmentManager(),this);
//        ViewPager viewPager = findViewById(R.id.view_pager_scan);
//        viewPager.setAdapter(sectionPagerAdapter);
//        TabLayout tabLayout = findViewById(R.id.tab_scan);
//        tabLayout.setupWithViewPager(viewPager);
//        getSupportActionBar().setElevation(0);
//
//        permission();
//    }
//
//    private void permission() {
//        new AskPermission.Builder(this).setPermissions(
//                Manifest.permission.CAMERA)
//                .setCallback(this)
//                .setErrorCallback(this)
//                .request(REQUEST_PERMISSIONS);
//
//    }
//
//    @Override
//    public void onShowRationalDialog(final PermissionInterface permissionInterface, int requestCode) {
////
//        permissionInterface.onDialogShown();
//    }
//
//    @Override
//    public void onShowSettings(final PermissionInterface permissionInterface, int requestCode) {
//
//    }
//
//    @Override
//    public void onPermissionsGranted(int requestCode) {
//
//    }
//
//    @Override
//    public void onPermissionsDenied(int requestCode) {
//        Intent intent = new Intent(getApplicationContext(), KelolaPeserta.class);
//        startActivity(intent);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                onBackPressed();
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//}
//
