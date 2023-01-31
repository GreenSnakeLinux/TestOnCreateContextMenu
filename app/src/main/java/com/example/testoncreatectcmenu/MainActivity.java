package com.example.testoncreatectcmenu;

import android.content.Context;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private WebView m_webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_main);
        m_webView = findViewById(R.id.main_view);

        registerForContextMenu(m_webView);
        m_webView.loadUrl("https://www.google.com");
    }

    public static void copyToClipboard(Context context, String text) {
        android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        android.content.ClipData clip = android.content.ClipData.newPlainText("clip", text);
        if(clipboard!=null)
            clipboard.setPrimaryClip(clip);
    }

    // Do a long press on main layout to call onCreateContextMenu
    @Override
    public void onCreateContextMenu(@NonNull ContextMenu contextMenu, @NonNull View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        super.onCreateContextMenu(contextMenu, view, contextMenuInfo);
        try {
            final WebView.HitTestResult webViewHitTestResult = m_webView.getHitTestResult();

            //if (webViewHitTestResult.getType() == WebView.HitTestResult.EDIT_TEXT_TYPE) {
            contextMenu.add(0, 1, 0, "Copy")
                    .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {
                            try {
                                String text = webViewHitTestResult.getExtra();
                                copyToClipboard(MainActivity.this, text);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            return false;
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
