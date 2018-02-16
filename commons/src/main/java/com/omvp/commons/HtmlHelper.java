package com.omvp.commons;

import android.net.Uri;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.util.Patterns;
import android.view.View;
import android.widget.TextView;


/**
 * Created by Ángel Gómez on 28/05/2017.
 */

public class HtmlHelper {

    public static void setText(TextView textView, String htmlContent) {
        setText(textView, htmlContent, null);
    }

    public static void setText(TextView textView, String htmlContent, OnHyperlinkClickListener listener) {
        if (!TextUtils.isEmpty(htmlContent)) {
            CharSequence sequence = fromHtml(htmlContent);
            SpannableStringBuilder strBuilder = new SpannableStringBuilder(sequence);
            URLSpan[] urls = strBuilder.getSpans(0, sequence.length(), URLSpan.class);
            if (urls != null && urls.length > 0) {
                for (URLSpan span : urls) {
                    makeLinkClickable(strBuilder, span, listener);
                }
            } else {
                boolean hasLinks = false;
                for (String word : htmlContent.split(" ")) {
                    if (Patterns.WEB_URL.matcher(word).matches()) {
                        hasLinks = true;
                        htmlContent = htmlContent.replace(word, "<a href='"+word+"'>"+word+"</a>");
                    }
                }
                if (hasLinks) {
                    setText(textView, htmlContent, listener);
                    return;
                }
            }
            textView.setText(strBuilder);
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        } else {
            textView.setText("");
        }
    }

    public static CharSequence fromHtml(String html) {
        CharSequence result;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(html);
        }
        return result;
    }

    private static void makeLinkClickable(SpannableStringBuilder strBuilder, final URLSpan span, final OnHyperlinkClickListener listener) {
        int start = strBuilder.getSpanStart(span);
        int end = strBuilder.getSpanEnd(span);
        int flags = strBuilder.getSpanFlags(span);
        ClickableSpan clickable = new ClickableSpan() {
            public void onClick(View view) {
                Uri uri = Uri.parse(span.getURL());
                if (listener != null) {
                    listener.onHyperlinkClick(uri);
                }
            }
        };
        strBuilder.setSpan(clickable, start, end, flags);
        strBuilder.removeSpan(span);
    }

}
