package com.fct.kosmos.view.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import com.fct.kosmos.R;
import com.fct.kosmos.model.Product;
import com.fct.kosmos.model.Warehouse;
import com.fct.kosmos.view.adapter.CatalogAdapter;

import java.util.List;

public class CatalogActivity extends AppCompatActivity {

	private RecyclerView recyclerView;
	private CatalogAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_log_in);

		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		DrawerLayout drawer = findViewById(R.id.drawer_layout);
		NavigationView navigationView = findViewById(R.id.nav_view);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
				this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		drawer.addDrawerListener(toggle);
		toggle.syncState();
		navigationView.setNavigationItemSelectedListener(this::onNavigationItemSelected);

		FloatingActionButton fab = findViewById(R.id.fab);
		fab.setOnClickListener(view -> {
			Product product = new Product();
			Warehouse.getInstance(this).addProduct(product);
            Intent intent = ProductActivity.newIntent(this, product.getId());
            startActivity(intent);
        });

		// Find the ListView which will be populated with the pet data
		recyclerView = findViewById(R.id.list);
		recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
		recyclerView.setHasFixedSize(true);

		// Setup an Adapter to create a list item for each row of product data in the Cursor.
		Warehouse warehouse = Warehouse.getInstance(this);
		List<Product> products = warehouse.getProducts();

		adapter = new CatalogAdapter(this, products);
		recyclerView.setAdapter(adapter);
	}

	@Override
	protected void onResume() {
		Warehouse warehouse = Warehouse.getInstance(this);
		List<Product> products = warehouse.getProducts();

		if (adapter == null) {
			adapter = new CatalogAdapter(this, products);
			recyclerView.setAdapter(adapter);
		} else {
			adapter.setData(products);
			adapter.notifyDataSetChanged();
		}
		super.onResume();
	}


	@Override
	public void onBackPressed() {
		DrawerLayout drawer = findViewById(R.id.drawer_layout);
		if (drawer.isDrawerOpen(GravityCompat.START)) {
			drawer.closeDrawer(GravityCompat.START);
		} else {
			super.onBackPressed();
		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home_log_in, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_search) {
			return true;
		}else if (id == R.id.action_scanner ){
			//  launchActivity(ScannerUnoActivity.class);
		}


		return super.onOptionsItemSelected(item);
	}

	@SuppressWarnings("StatementWithEmptyBody")


	public boolean onNavigationItemSelected(MenuItem item) {
		// Handle navigation view item clicks here.
		int id = item.getItemId();

		if (id == R.id.nav_home) {
		//	Intent i = new Intent(this, HomeLogIn.class);
		//	startActivity(i);

		} else if (id == R.id.nav_gallery) {

		} else if (id == R.id.nav_slideshow) {

		} else if (id == R.id.nav_tools) {

		} else if (id == R.id.nav_share) {

		} else if (id == R.id.nav_send) {

		}

		DrawerLayout drawer = findViewById(R.id.drawer_layout);
		drawer.closeDrawer(GravityCompat.START);
		return true;
	}
}
