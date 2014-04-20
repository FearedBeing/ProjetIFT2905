[33mcommit 228989225ad32e33b3f44ce0d89e3a8caa6bdd66[m
Merge: 7702f5d b031e52
Author: Guillaume Roy-Loranger <g.roy.loranger@gmail.com>
Date:   Sat Apr 19 23:21:02 2014 -0400

    Le menu principal affiche maintenant une liste de tournois

[33mcommit b031e52584552b89131bb1ee84635e07ce33be4a[m
Author: Guillaume Roy-Loranger <g.roy.loranger@gmail.com>
Date:   Sat Apr 19 22:39:21 2014 -0400

    Les tournois sont maintenants affiches dans le menu principal

[1mdiff --git a/AndroidManifest.xml b/AndroidManifest.xml[m
[1mindex 4104a88..ae31e2f 100644[m
[1m--- a/AndroidManifest.xml[m
[1m+++ b/AndroidManifest.xml[m
[36m@@ -7,6 +7,7 @@[m
     <uses-sdk[m
         android:minSdkVersion="14"[m
         android:targetSdkVersion="14" />[m
[32m+[m[32m    <uses-permission android:name="android.permission.INTERNET"/>[m
 [m
     <application[m
         android:allowBackup="true"[m
[1mdiff --git a/bin/AndroidManifest.xml b/bin/AndroidManifest.xml[m
[1mindex 4104a88..ae31e2f 100644[m
[1m--- a/bin/AndroidManifest.xml[m
[1m+++ b/bin/AndroidManifest.xml[m
[36m@@ -7,6 +7,7 @@[m
     <uses-sdk[m
         android:minSdkVersion="14"[m
         android:targetSdkVersion="14" />[m
[32m+[m[32m    <uses-permission android:name="android.permission.INTERNET"/>[m
 [m
     <application[m
         android:allowBackup="true"[m
[1mdiff --git a/bin/ProjetIFT2905.apk b/bin/ProjetIFT2905.apk[m
[1mindex 5d729d5..8c2f8e0 100644[m
Binary files a/bin/ProjetIFT2905.apk and b/bin/ProjetIFT2905.apk differ
[1mdiff --git a/bin/classes.dex b/bin/classes.dex[m
[1mindex 899c0f9..f43d9a3 100644[m
Binary files a/bin/classes.dex and b/bin/classes.dex differ
[1mdiff --git a/bin/classes/com/example/projetift2905/MainActivity$1.class b/bin/classes/com/example/projetift2905/MainActivity$1.class[m
[1mindex 8297154..01dcf4d 100644[m
Binary files a/bin/classes/com/example/projetift2905/MainActivity$1.class and b/bin/classes/com/example/projetift2905/MainActivity$1.class differ
[1mdiff --git a/bin/classes/com/example/projetift2905/MainActivity$DownloadLoginTask.class b/bin/classes/com/example/projetift2905/MainActivity$DownloadLoginTask.class[m
[1mnew file mode 100644[m
[1mindex 0000000..601567c[m
Binary files /dev/null and b/bin/classes/com/example/projetift2905/MainActivity$DownloadLoginTask.class differ
[1mdiff --git a/bin/classes/com/example/projetift2905/MainActivity$PagerAdapter.class b/bin/classes/com/example/projetift2905/MainActivity$PagerAdapter.class[m
[1mindex 64a42c9..05afcb8 100644[m
Binary files a/bin/classes/com/example/projetift2905/MainActivity$PagerAdapter.class and b/bin/classes/com/example/projetift2905/MainActivity$PagerAdapter.class differ
[1mdiff --git a/bin/classes/com/example/projetift2905/MainActivity.class b/bin/classes/com/example/projetift2905/MainActivity.class[m
[1mindex 41733a4..b674256 100644[m
Binary files a/bin/classes/com/example/projetift2905/MainActivity.class and b/bin/classes/com/example/projetift2905/MainActivity.class differ
[1mdiff --git a/bin/classes/com/example/projetift2905/MainPagerFragment$MainListOnItemClick.class b/bin/classes/com/example/projetift2905/MainPagerFragment$MainListOnItemClick.class[m
[1mnew file mode 100644[m
[1mindex 0000000..b5479cf[m
Binary files /dev/null and b/bin/classes/com/example/projetift2905/MainPagerFragment$MainListOnItemClick.class differ
[1mdiff --git a/bin/classes/com/example/projetift2905/MainPagerFragment.class b/bin/classes/com/example/projetift2905/MainPagerFragment.class[m
[1mindex dd871f5..fbe15b4 100644[m
Binary files a/bin/classes/com/example/projetift2905/MainPagerFragment.class and b/bin/classes/com/example/projetift2905/MainPagerFragment.class differ
[1mdiff --git a/bin/classes/com/example/projetift2905/R$id.class b/bin/classes/com/example/projetift2905/R$id.class[m
[1mindex 928c847..fcfdc1f 100644[m
Binary files a/bin/classes/com/example/projetift2905/R$id.class and b/bin/classes/com/example/projetift2905/R$id.class differ
[1mdiff --git a/bin/classes/com/example/projetift2905/R$layout.class b/bin/classes/com/example/projetift2905/R$layout.class[m
[1mindex 2c87824..34d0785 100644[m
Binary files a/bin/classes/com/example/projetift2905/R$layout.class and b/bin/classes/com/example/projetift2905/R$layout.class differ
[1mdiff --git a/bin/classes/com/example/projetift2905/R$menu.class b/bin/classes/com/example/projetift2905/R$menu.class[m
[1mindex 72b5089..0d38d0a 100644[m
Binary files a/bin/classes/com/example/projetift2905/R$menu.class and b/bin/classes/com/example/projetift2905/R$menu.class differ
[1mdiff --git a/bin/classes/com/example/projetift2905/R$string.class b/bin/classes/com/example/projetift2905/R$string.class[m
[1mindex dcb50b0..817bc46 100644[m
Binary files a/bin/classes/com/example/projetift2905/R$string.class and b/bin/classes/com/example/projetift2905/R$string.class differ
[1mdiff --git a/bin/classes/com/example/projetift2905/R$style.class b/bin/classes/com/example/projetift2905/R$style.class[m
[1mindex 2236849..ade3367 100644[m
Binary files a/bin/classes/com/example/projetift2905/R$style.class and b/bin/classes/com/example/projetift2905/R$style.class differ
[1mdiff --git a/bin/classes/com/example/projetift2905/SelectionTournoiAPI$TourneyData.class b/bin/classes/com/example/projetift2905/SelectionTournoiAPI$TourneyData.class[m
[1mnew file mode 100644[m
[1mindex 0000000..ec3a769[m
Binary files /dev/null and b/bin/classes/com/example/projetift2905/SelectionTournoiAPI$TourneyData.class differ
[1mdiff --git a/bin/classes/com/example/projetift2905/SelectionTournoiAPI.class b/bin/classes/com/example/projetift2905/SelectionTournoiAPI.class[m
[1mnew file mode 100644[m
[1mindex 0000000..e6a14d1[m
Binary files /dev/null and b/bin/classes/com/example/projetift2905/SelectionTournoiAPI.class differ
[1mdiff --git a/bin/classes/com/example/projetift2905/TourneyAdapter.class b/bin/classes/com/example/projetift2905/TourneyAdapter.class[m
[1mnew file mode 100644[m
[1mindex 0000000..0fa6e1b[m
Binary files /dev/null and b/bin/classes/com/example/projetift2905/TourneyAdapter.class differ
[1mdiff --git a/bin/classes/com/example/projetift2905/TournoiAdapter$TournoiData.class b/bin/classes/com/example/projetift2905/TournoiAdapter$TournoiData.class[m
[1mdeleted file mode 100644[m
[1mindex 73393ef..0000000[m
Binary files a/bin/classes/com/example/projetift2905/TournoiAdapter$TournoiData.class and /dev/null differ
[1mdiff --git a/bin/classes/com/example/projetift2905/TournoiAdapter.class b/bin/classes/com/example/projetift2905/TournoiAdapter.class[m
[1mdeleted file mode 100644[m
[1mindex 92ec684..0000000[m
Binary files a/bin/classes/com/example/projetift2905/TournoiAdapter.class and /dev/null differ
[1mdiff --git a/bin/resources.ap_ b/bin/resources.ap_[m
[1mindex 9a67037..02702bd 100644[m
Binary files a/bin/resources.ap_ and b/bin/resources.ap_ differ
[1mdiff --git a/gen/com/example/projetift2905/R.java b/gen/com/example/projetift2905/R.java[m
[1mindex f29a748..d5460e8 100644[m
[1m--- a/gen/com/example/projetift2905/R.java[m
[1m+++ b/gen/com/example/projetift2905/R.java[m
[36m@@ -25,12 +25,13 @@[m [mpublic final class R {[m
         public static final int ic_launcher=0x7f020000;[m
     }[m
     public static final class id {[m
[31m-        public static final int LogoImage=0x7f080005;[m
[31m-        public static final int action_settings=0x7f080006;[m
[32m+[m[32m        public static final int LogoImage=0x7f080006;[m
[32m+[m[32m        public static final int action_settings=0x7f080007;[m
         public static final int container=0x7f080000;[m
[31m-        public static final int favoriButton=0x7f080002;[m
[31m-        public static final int nomTournoi=0x7f080004;[m
[31m-        public static final int ownedImage=0x7f080003;[m
[32m+[m[32m        public static final int favoriButton=0x7f080003;[m
[32m+[m[32m        public static final int listTournois=0x7f080002;[m
[32m+[m[32m        public static final int nomTournoi=0x7f080005;[m
[32m+[m[32m        public static final int ownedImage=0x7f080004;[m
         public static final int pager=0x7f080001;[m
     }[m
     public static final class layout {[m
[36m@@ -42,13 +43,15 @@[m [mpublic final class R {[m
         public static final int main=0x7f070000;[m
     }[m
     public static final class string {[m
[31m-        public static final int action_settings=0x7f050002;[m
[32m+[m[32m        public static final int action_settings=0x7f050001;[m
[32m+[m[32m        public static final int api_vide=0x7f050006;[m
         public static final int app_name=0x7f050000;[m
[31m-        public static final int hello_world=0x7f050001;[m
[31m-        public static final int title_activity_=0x7f050003;[m
[31m-        public static final int title_section1=0x7f050004;[m
[31m-        public static final int title_section2=0x7f050005;[m
[31m-        public static final int title_section3=0x7f050006;[m
[32m+[m[32m        /**  Ressources pour MainActivity[m[41m [m
[32m+[m[32m         */[m
[32m+[m[32m        public static final int title_activity_=0x7f050002;[m
[32m+[m[32m        public static final int title_section1=0x7f050003;[m
[32m+[m[32m        public static final int title_section2=0x7f050004;[m
[32m+[m[32m        public static final int title_section3=0x7f050005;[m
     }[m
     public static final class style {[m
         /** [m
[1mdiff --git a/res/layout/fragment_main.xml b/res/layout/fragment_main.xml[m
[1mindex 435138e..de3b1ef 100644[m
[1m--- a/res/layout/fragment_main.xml[m
[1m+++ b/res/layout/fragment_main.xml[m
[36m@@ -1,8 +1,17 @@[m
 <?xml version="1.0" encoding="utf-8"?>[m
 <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"[m
[31m-    android:layout_width="match_parent"[m
[31m-    android:layout_height="match_parent"[m
[32m+[m[32m    android:layout_width="fill_parent"[m
[32m+[m[32m    android:layout_height="fill_parent"[m
[32m+[m[32m    android:background="#999999"[m
     android:orientation="vertical" >[m
[31m-    [m
[32m+[m
[32m+[m[32m    <ListView[m
[32m+[m[32m        android:id="@+id/listTournois"[m
[32m+[m[32m        android:layout_width="fill_parent"[m
[32m+[m[32m        android:layout_height="0dp"[m
[32m+[m[32m        android:layout_weight="1"[m
[32m+[m[32m        android:background="#FFFFFF" >[m
[32m+[m
[32m+[m[32m    </ListView>[m
 [m
 </LinearLayout>[m
[1mdiff --git a/res/layout/tournoi_element.xml b/res/layout/tournoi_element.xml[m
[1mindex 032bc00..9954b25 100644[m
[1m--- a/res/layout/tournoi_element.xml[m
[1m+++ b/res/layout/tournoi_element.xml[m
[36m@@ -1,7 +1,7 @@[m
 <?xml version="1.0" encoding="utf-8"?>[m
 <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"[m
[31m-    android:layout_width="match_parent"[m
[31m-    android:layout_height="match_parent"[m
[32m+[m[32m    android:layout_width="fill_parent"[m
[32m+[m[32m    android:layout_height="wrap_content"[m
     android:orientation="horizontal" >[m
 [m
     <ImageButton[m
[1mdiff --git a/res/values/strings.xml b/res/values/strings.xml[m
[1mindex 18f87fa..2d364ca 100644[m
[1m--- a/res/values/strings.xml[m
[1m+++ b/res/values/strings.xml[m
[36m@@ -2,11 +2,13 @@[m
 <resources>[m
 [m
     <string name="app_name">ProjetIFT2905</string>[m
[31m-    <string name="hello_world">Hello world!</string>[m
     <string name="action_settings">Settings</string>[m
[31m-    <string name="title_activity_">A</string>[m
[32m+[m[41m    [m
[32m+[m[32m    <!-- Ressources pour MainActivity -->[m
[32m+[m[32m    <string name="title_activity_">SelectionTournoi</string>[m
     <string name="title_section1">Tous les tournois</string>[m
     <string name="title_section2">Favoris</string>[m
     <string name="title_section3">Mes tournois</string>[m
[32m+[m[32m    <string name="api_vide">API Vide</string>[m
 [m
 </resources>[m
[1mdiff --git a/src/com/example/projetift2905/MainActivity.java b/src/com/example/projetift2905/MainActivity.java[m
[1mindex eb7f1fc..196cc2d 100644[m
[1m--- a/src/com/example/projetift2905/MainActivity.java[m
[1m+++ b/src/com/example/projetift2905/MainActivity.java[m
[36m@@ -1,25 +1,45 @@[m
 package com.example.projetift2905;[m
 [m
[32m+[m[32mimport java.util.ArrayList;[m
[32m+[m[32mimport java.util.List;[m
[32m+[m
 import android.app.ActionBar;[m
 import android.app.FragmentTransaction;[m
[32m+[m[32mimport android.os.AsyncTask;[m
 import android.os.Bundle;[m
 import android.support.v4.app.Fragment;[m
 import android.support.v4.app.FragmentActivity;[m
 import android.support.v4.app.FragmentManager;[m
 import android.support.v4.app.FragmentPagerAdapter;[m
 import android.support.v4.view.ViewPager;[m
[32m+[m[32mimport android.util.Log;[m
 import android.view.Menu;[m
 import android.view.MenuItem;[m
[32m+[m[32mimport android.view.Window;[m
[32m+[m[32mimport android.widget.Toast;[m
 [m
 public class MainActivity extends FragmentActivity {[m
 [m
 	PagerAdapter adapter;[m
 	ViewPager pager;[m
[32m+[m	[32mSelectionTournoiAPI api;[m
[32m+[m	[32mList<MainPagerFragment> fragList;[m
 	[m
 	protected void onCreate(Bundle savedInstanceState) {[m
 		super.onCreate(savedInstanceState);[m
[32m+[m[41m		[m
[32m+[m		[32m/* **************************************[m
[32m+[m		[32m * APPEL API POUR CREER LISTE DE TOURNOIS[m
[32m+[m		[32m * **************************************/[m
[32m+[m		[32mgetWindow().requestFeature(Window.FEATURE_INDETERMINATE_PROGRESS);[m
 		setContentView(R.layout.activity_main);[m
[32m+[m		[32mthis.api = null;[m
[32m+[m[32m        new DownloadLoginTask().execute();[m
 		[m
[32m+[m		[32m/* *******************************[m
[32m+[m		[32m * CREATION DE LA BARRE D'ONGLETS[m
[32m+[m		[32m * *******************************/[m
[32m+[m[32m        fragList = new ArrayList<MainPagerFragment>();[m
 		adapter = new PagerAdapter(getSupportFragmentManager());[m
         pager = (ViewPager) findViewById(R.id.pager);[m
         pager.setAdapter(adapter);[m
[36m@@ -45,11 +65,61 @@[m [mpublic class MainActivity extends FragmentActivity {[m
                             .setTabListener(tabListener));[m
         }[m
         [m
[31m-       [m
[32m+[m[41m        [m
[32m+[m	[32m}[m
[32m+[m[41m	[m
[32m+[m	[32mpublic void setApi(SelectionTournoiAPI api){[m
[32m+[m		[32mthis.api = api;[m
 	}[m
 	[m
[32m+[m	[32mpublic SelectionTournoiAPI getApi(){[m
[32m+[m		[32mreturn this.api;[m
[32m+[m	[32m}[m
[32m+[m[41m	[m
[32m+[m	[32mprivate class DownloadLoginTask extends AsyncTask<String, String, SelectionTournoiAPI> {[m
[32m+[m[41m		[m
[32m+[m		[32mprotected void onPreExecute() {[m
[32m+[m			[32msetProgressBarIndeterminateVisibility(true);[m
[32m+[m		[32m}[m
[32m+[m[41m		[m
[32m+[m		[32mprotected SelectionTournoiAPI doInBackground(String... params) {[m
[32m+[m			[32mSelectionTournoiAPI api = new SelectionTournoiAPI();[m
[32m+[m			[32mreturn api;[m
[32m+[m		[32m}[m
[32m+[m[41m		[m
[32m+[m		[32mprotected void onProgressUpdate(String... s) {}[m
[32m+[m[41m		[m
[32m+[m		[32mprotected void onPostExecute(SelectionTournoiAPI api) {[m
[32m+[m			[32msetProgressBarIndeterminateVisibility(false);[m
[32m+[m[41m			[m
[32m+[m			[32m// On s'assure que l'objet de retour existe[m
[32m+[m			[32m// et qu'il n'ait pas d'erreurs[m
[32m+[m			[32mif( api == null ) {[m
[32m+[m				[32mToast.makeText(MainActivity.this, getText(R.string.api_vide), Toast.LENGTH_SHORT).show();[m
[32m+[m				[32mreturn;[m
[32m+[m			[32m}[m
[32m+[m[41m			[m
[32m+[m			[32mif( api.error != null ) {[m
[32m+[m				[32mToast.makeText(MainActivity.this, api.error, Toast.LENGTH_SHORT).show();[m
[32m+[m				[32mreturn;[m
[32m+[m			[32m}[m
[32m+[m[41m			[m
[32m+[m			[32msetApi(api);[m
[32m+[m[41m			[m
[32m+[m[41m			[m
[32m+[m			[32mfor(MainPagerFragment frg : fragList){[m
[32m+[m				[32mfrg.setTourneyData();[m
[32m+[m			[32m}[m
[32m+[m		[32m}[m
[32m+[m	[32m}[m[41m	[m
[32m+[m[41m	[m
 	private class PagerAdapter extends FragmentPagerAdapter{[m
 		[m
[32m+[m		[32m@Override[m
[32m+[m		[32mpublic long getItemId(int position) {[m
[32m+[m			[32mreturn super.getItemId(position);[m
[32m+[m		[32m}[m
[32m+[m
 		public PagerAdapter(FragmentManager fm){[m
 			super(fm);[m
 		}[m
[36m@@ -59,6 +129,7 @@[m [mpublic class MainActivity extends FragmentActivity {[m
 			Bundle args = new Bundle();[m
 			args.putInt("id", i);[m
 			f.setArguments(args);[m
[32m+[m			[32mfragList.add(f);[m
 			return f;[m
 		}[m
 		[m
[1mdiff --git a/src/com/example/projetift2905/MainPagerFragment.java b/src/com/example/projetift2905/MainPagerFragment.java[m
[1mindex e9e844d..79e3d4e 100644[m
[1m--- a/src/com/example/projetift2905/MainPagerFragment.java[m
[1m+++ b/src/com/example/projetift2905/MainPagerFragment.java[m
[36m@@ -1,17 +1,60 @@[m
 package com.example.projetift2905;[m
 [m
[32m+[m[32mimport java.util.ArrayList;[m
[32m+[m[32mimport java.util.List;[m
[32m+[m
[32m+[m[32mimport android.graphics.Color;[m
 import android.os.Bundle;[m
 import android.support.v4.app.Fragment;[m
[32m+[m[32mimport android.util.Log;[m
 import android.view.LayoutInflater;[m
 import android.view.View;[m
 import android.view.ViewGroup;[m
[32m+[m[32mimport android.widget.AdapterView;[m
[32m+[m[32mimport android.widget.AdapterView.OnItemClickListener;[m
[32m+[m[32mimport android.widget.ListView;[m
[32m+[m[32mimport android.widget.Toast;[m
[32m+[m
[32m+[m[32mimport com.example.projetift2905.SelectionTournoiAPI.TourneyData;[m
 [m
 public class MainPagerFragment extends Fragment{[m
 	[m
[32m+[m	[32mprivate List<TourneyData> dataList;[m
[32m+[m	[32mprivate ListView mainList;[m
[32m+[m	[32mprivate TourneyAdapter mainAdapter;[m
[32m+[m[41m	[m
     @Override[m
 	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {[m
     	View rootView = inflater.inflate(R.layout.fragment_main, container, false);[m
         Bundle args = getArguments();[m
[32m+[m[41m        [m
[32m+[m[32m        this.dataList = new ArrayList<TourneyData>();[m
[32m+[m[32m        this.mainAdapter = new TourneyAdapter(this.getActivity().getApplicationContext(), this.dataList);[m
[32m+[m[32m        this.setTourneyData();[m
[32m+[m[32m        mainList = (ListView) rootView.findViewById(R.id.listTournois);[m
[32m+[m[32m        mainList.setAdapter(mainAdapter);[m
[32m+[m[32m        mainList.setOnItemClickListener(new MainListOnItemClick());[m
[32m+[m[41m        [m
         return rootView;[m
     }[m
[31m-}[m
[32m+[m[41m    [m
[32m+[m[32m    public void setTourneyData(){[m
[32m+[m[41m    [m	[32mMainActivity activity = (MainActivity)this.getActivity();[m
[32m+[m[41m    [m	[32mSelectionTournoiAPI api = activity.getApi();[m
[32m+[m[41m    [m	[32mif(api != null){[m
[32m+[m[41m    [m		[32mthis.dataList.clear();[m
[32m+[m[41m    [m		[32mthis.dataList.addAll(api.dataList);[m
[32m+[m[41m    [m		[32mthis.mainAdapter.notifyDataSetChanged();[m
[32m+[m[41m    [m	[32m}[m
[32m+[m[32m    }[m
[32m+[m[41m    [m
[32m+[m[32m    private class MainListOnItemClick implements OnItemClickListener{[m
[32m+[m		[32mpublic void onItemClick(AdapterView<?> adapter, View view, int position, long id) {[m
[32m+[m			[32mswitch(position){[m
[32m+[m			[32mdefault:[m
[32m+[m				[32mToast.makeText(getActivity().getApplicationContext(), "Position " + position, Toast.LENGTH_SHORT).show();[m
[32m+[m[41m					[m
[32m+[m			[32m}[m
[32m+[m		[32m}[m
[32m+[m	[32m}[m
[32m+[m[32m}[m[41m    [m
\ No newline at end of file[m
[1mdiff --git a/src/com/example/projetift2905/SelectionTournoiAPI.java b/src/com/example/projetift2905/SelectionTournoiAPI.java[m
[1mnew file mode 100644[m
[1mindex 0000000..9041b08[m
[1m--- /dev/null[m
[1m+++ b/src/com/example/projetift2905/SelectionTournoiAPI.java[m
[36m@@ -0,0 +1,91 @@[m
[32m+[m[32mpackage com.example.projetift2905;[m
[32m+[m
[32m+[m[32mimport java.io.IOException;[m
[32m+[m[32mimport java.io.InputStream;[m
[32m+[m[32mimport java.util.ArrayList;[m
[32m+[m[32mimport java.util.List;[m
[32m+[m
[32m+[m[32mimport org.apache.http.HttpEntity;[m
[32m+[m[32mimport org.apache.http.HttpResponse;[m
[32m+[m[32mimport org.apache.http.ParseException;[m
[32m+[m[32mimport org.apache.http.client.ClientProtocolException;[m
[32m+[m[32mimport org.apache.http.client.HttpClient;[m
[32m+[m[32mimport org.apache.http.client.methods.HttpGet;[m
[32m+[m[32mimport org.apache.http.impl.client.DefaultHttpClient;[m
[32m+[m[32mimport org.apache.http.protocol.HTTP;[m
[32m+[m[32mimport org.apache.http.util.EntityUtils;[m
[32m+[m[32mimport org.json.JSONArray;[m
[32m+[m[32mimport org.json.JSONException;[m
[32m+[m[32mimport org.json.JSONObject;[m
[32m+[m
[32m+[m[32mimport android.graphics.drawable.Drawable;[m
[32m+[m[32mimport android.util.Log;[m
[32m+[m
[32m+[m[32mpublic class SelectionTournoiAPI {[m
[32m+[m[41m	[m
[32m+[m	[32mpublic List<TourneyData> dataList;[m
[32m+[m	[32mpublic String error;[m
[32m+[m[41m	[m
[32m+[m	[32mpublic class TourneyData{[m
[32m+[m		[32mpublic final String title, tourneyID;[m
[32m+[m		[32mpublic final Drawable gameLogo;[m
[32m+[m[41m		[m
[32m+[m		[32mpublic TourneyData(String tourneyID, String title, Drawable gameLogo){[m
[32m+[m			[32mthis.tourneyID = tourneyID;[m
[32m+[m			[32mthis.title = title;[m
[32m+[m			[32mthis.gameLogo = gameLogo;[m
[32m+[m		[32m}[m
[32m+[m	[32m}[m
[32m+[m[41m	[m
[32m+[m	[32mSelectionTournoiAPI(){[m
[32m+[m		[32merror = null;[m
[32m+[m		[32mdataList = new ArrayList<TourneyData>();[m
[32m+[m		[32mString apiCall = "https://api.binarybeast.com/?APIService=Tourney.TourneyList.Popular&APIReturn=json&APIKey=4904a28e5c92f5919f7fcc2e716597e8.5350362f7b2669.06156210&Limit=25";[m
[32m+[m[41m		[m
[32m+[m		[32mtry{[m
[32m+[m[41m			[m
[32m+[m			[32mHttpEntity page = getHttp(apiCall);[m
[32m+[m			[32mJSONObject js = new JSONObject(EntityUtils.toString(page, HTTP.UTF_8));[m
[32m+[m			[32mJSONArray list = js.getJSONArray("List");[m
[32m+[m[41m			[m
[32m+[m			[32mfor(int index=0; index<list.length(); index++){[m
[32m+[m				[32mJSONObject element = list.getJSONObject(index);[m
[32m+[m				[32mString tourneyID = element.getString("TourneyID");[m
[32m+[m				[32mString title = element.getString("Title");[m
[32m+[m				[32mString gameIconURL = element.getString("GameIcon");[m
[32m+[m				[32mLog.d("DATA", tourneyID);[m
[32m+[m				[32mLog.d("DATA", title);[m
[32m+[m				[32mLog.d("DATA", gameIconURL);[m
[32m+[m				[32mDrawable gameLogo = null;[m
[32m+[m				[32mif( !gameIconURL.equals("null") ) {[m
[32m+[m					[32mgameLogo = loadHttpImage(gameIconURL);[m
[32m+[m				[32m}[m
[32m+[m				[32mdataList.add(new TourneyData(tourneyID, title, gameLogo));[m
[32m+[m			[32m}[m
[32m+[m[41m			[m
[32m+[m		[32m} catch (ClientProtocolException e) {[m
[32m+[m			[32merror = "Erreur HTTP (protocole) :"+e.getMessage();[m
[32m+[m		[32m} catch (IOException e) {[m
[32m+[m			[32merror = "Erreur HTTP (IO) :"+e.getMessage();[m
[32m+[m		[32m} catch (ParseException e) {[m
[32m+[m			[32merror = "Erreur JSON (parse) :"+e.getMessage();[m
[32m+[m		[32m} catch (JSONException e) {[m
[32m+[m			[32merror = "Erreur JSON :"+e.getMessage();[m
[32m+[m		[32m}[m[41m	[m
[32m+[m	[32m}[m
[32m+[m[41m	[m
[32m+[m	[32mprivate HttpEntity getHttp(String url) throws ClientProtocolException, IOException {[m
[32m+[m		[32mHttpClient httpClient = new DefaultHttpClient();[m
[32m+[m		[32mHttpGet http = new HttpGet(url);[m
[32m+[m		[32mHttpResponse response = httpClient.execute(http);[m
[32m+[m		[32mreturn response.getEntity();[m[41m    		[m
[32m+[m	[32m}[m
[32m+[m[41m	[m
[32m+[m	[32mprivate Drawable loadHttpImage(String url) throws ClientProtocolException, IOException {[m
[32m+[m		[32mInputStream is = getHttp(url).getContent();[m
[32m+[m		[32mDrawable d = Drawable.createFromStream(is, "src");[m
[32m+[m		[32mreturn d;[m
[32m+[m	[32m}[m
[32m+[m[41m	[m
[32m+[m
[32m+[m[32m}[m
[1mdiff --git a/src/com/example/projetift2905/TourneyAdapter.java b/src/com/example/projetift2905/TourneyAdapter.java[m
[1mnew file mode 100644[m
[1mindex 0000000..c5179d5[m
[1m--- /dev/null[m
[1m+++ b/src/com/example/projetift2905/TourneyAdapter.java[m
[36m@@ -0,0 +1,65 @@[m
[32m+[m[32mpackage com.example.projetift2905;[m
[32m+[m
[32m+[m[32mimport java.util.List;[m
[32m+[m
[32m+[m[32mimport android.content.Context;[m
[32m+[m[32mimport android.util.Log;[m
[32m+[m[32mimport android.view.LayoutInflater;[m
[32m+[m[32mimport android.view.View;[m
[32m+[m[32mimport android.view.ViewGroup;[m
[32m+[m[32mimport android.widget.BaseAdapter;[m
[32m+[m[32mimport android.widget.TextView;[m
[32m+[m
[32m+[m[32mimport com.example.projetift2905.SelectionTournoiAPI.TourneyData;[m
[32m+[m
[32m+[m[32mpublic class TourneyAdapter extends BaseAdapter{[m[41m	[m
[32m+[m[41m	[m
[32m+[m	[32mprivate Context context;[m
[32m+[m	[32mprivate List<TourneyData> data;[m
[32m+[m[41m	[m
[32m+[m	[32mpublic TourneyAdapter(Context context, List<TourneyData> data){[m
[32m+[m		[32mthis.context = context;[m
[32m+[m		[32mthis.data = data;[m
[32m+[m	[32m}[m
[32m+[m[41m	[m
[32m+[m	[32m@Override[m
[32m+[m	[32mpublic int getCount() {[m
[32m+[m		[32mif(data != null){[m
[32m+[m			[32mreturn data.size();[m
[32m+[m		[32m}else{[m
[32m+[m			[32mreturn 0;[m
[32m+[m		[32m}[m
[32m+[m	[32m}[m
[32m+[m
[32m+[m	[32m@Override[m
[32m+[m	[32mpublic Object getItem(int position) {[m
[32m+[m		[32mif(data != null && position >= 0 && position < data.size())[m
[32m+[m			[32mreturn data.get(position);[m
[32m+[m		[32melse[m
[32m+[m			[32mreturn null;[m
[32m+[m	[32m}[m
[32m+[m
[32m+[m	[32m@Override[m
[32m+[m	[32mpublic long getItemId(int position) {[m
[32m+[m		[32mreturn position;[m
[32m+[m	[32m}[m
[32m+[m
[32m+[m	[32m@Override[m
[32m+[m	[32mpublic View getView(int position, View view, ViewGroup parent) {[m
[32m+[m		[32mTourneyData tourneyData = data.get(position);[m
[32m+[m[41m		[m
[32m+[m		[32mif(view == null){[m
[32m+[m			[32mLayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);[m
[32m+[m			[32mview = inflater.inflate(R.layout.tournoi_element, parent, false);[m
[32m+[m		[32m}[m
[32m+[m[41m		[m
[32m+[m		[32mTextView nomTournoi = (TextView) view.findViewById(R.id.nomTournoi);[m
[32m+[m		[32mnomTournoi.setText(tourneyData.title);[m
[32m+[m[41m		[m
[32m+[m		[32m// DOIT AJOUTER AVEC VIEWS DU LAYOUT[m
[32m+[m[41m		[m
[32m+[m		[32mreturn view;[m
[32m+[m	[32m}[m
[32m+[m
[32m+[m[41m	[m
[32m+[m[32m}[m
[1mdiff --git a/src/com/example/projetift2905/TournoiAdapter.java b/src/com/example/projetift2905/TournoiAdapter.java[m
[1mdeleted file mode 100644[m
[1mindex 2944e06..0000000[m
[1m--- a/src/com/example/projetift2905/TournoiAdapter.java[m
[1m+++ /dev/null[m
[36m@@ -1,69 +0,0 @@[m
[31m-package com.example.projetift2905;[m
[31m-[m
[31m-import java.util.List;[m
[31m-[m
[31m-import android.content.Context;[m
[31m-import android.view.LayoutInflater;[m
[31m-import android.view.View;[m
[31m-import android.view.ViewGroup;[m
[31m-import android.widget.BaseAdapter;[m
[31m-import android.widget.TextView;[m
[31m-[m
[31m-public class TournoiAdapter extends BaseAdapter{[m
[31m-	[m
[31m-	public static class TournoiData{[m
[31m-		[m
[31m-		public final boolean favori, proprietaire;[m
[31m-		public final String nom, jeu;[m
[31m-		[m
[31m-		public TournoiData(String nom, String jeu, boolean favori, boolean proprietaire){[m
[31m-			this.nom = nom;[m
[31m-			this.jeu = jeu;[m
[31m-			this.favori = false; // TEMPORAIRE AVANT D'AJOUTER LES FAVORIS[m
[31m-			this.proprietaire = false; // TEMPORAIRE AVANT D'AJOUTER CETTE CATEGORIE[m
[31m-		}[m
[31m-	}[m
[31m-	[m
[31m-	private List<TournoiData> data;[m
[31m-	private Context context;[m
[31m-	[m
[31m-[m
[31m-	@Override[m
[31m-	public int getCount() {[m
[31m-		if(data != null)[m
[31m-			return data.size();[m
[31m-		else[m
[31m-			return 0;[m
[31m-	}[m
[31m-[m
[31m-	@Override[m
[31m-	public Object getItem(int position) {[m
[31m-		if(data != null && position >= 0 && position < data.size())[m
[31m-			return data.get(position);[m
[31m-		else[m
[31m-			return null;[m
[31m-	}[m
[31m-[m
[31m-	@Override[m
[31m-	public long getItemId(int position) {[m
[31m-		return position;[m
[31m-	}[m
[31m-[m
[31m-	@Override[m
[31m-	public View getView(int position, View view, ViewGroup parent) {[m
[31m-		TournoiData tourneyData = data.get(position);[m
[31m-		[m
[31m-		if(view == null){[m
[31m-			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);[m
[31m-			view = inflater.inflate(R.layout.tournoi_element, parent, false);[m
[31m-		}[m
[31m-		[m
[31m-		TextView nomTournoi = (TextView) view.findViewById(R.id.nomTournoi);[m
[31m-		nomTournoi.setText(tourneyData.nom);[m
[31m-		// DOIT AJOUTER AVEC VIEWS DU LAYOUT[m
[31m-		[m
[31m-		return view;[m
[31m-	}[m
[31m-[m
[31m-	[m
[31m-}[m
