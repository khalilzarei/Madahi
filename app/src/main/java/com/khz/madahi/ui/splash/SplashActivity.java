package com.khz.madahi.ui.splash;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.khz.madahi.R;
import com.khz.madahi.application.BaseActivity;
import com.khz.madahi.helper.Const;
import com.khz.madahi.models.Category;
import com.khz.madahi.models.Content;
import com.khz.madahi.ui.category.CategoryActivity;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if (sessionManager.isFirstTimeLaunch()) {
            putData();
        }
        new Handler().postDelayed(() -> startActivity(new Intent(SplashActivity.this, CategoryActivity.class)), 700);
    }

    void putData() {
        String[] title = {
                "مسلم بن عقیل(ع)",
                "ورود به کربلا",
                "حضرت رقیه(س)",
                "حضرت حر(ع)",
                "حضرت زهیر و عبدالله بن حسن و ... ",
                "حضرت قاسم(ع)",
                "حضرت علی اصغر(ع)",
                "حضرت علی اکبر(ع)",
                "شب تاسوعا",
                "شب عاشورا شام غریبان"
        };
        String[] night = {
                "شب اول",
                "شب دوم",
                "شب سوم",
                "شب چهارم",
                "شب پنجم",
                "شب ششم",
                "شب هفتم",
                "شب هشتم",
                "شب نهم",
                "شب دهم"
        };
        for (int i = 0; i < night.length; i++) {
            databaseHelper.categoryDAO().insert(new Category(String.valueOf(i + 1), title[i], night[i]));
        }
        String id         = "1";
        String categoryId = "3";
        String userId;
        String answer     = "من یک سه ساله دخترم مزن ای نیزه دار";
        String content = "<p>من یک سه ساله دخترم مزن ای نیزه دار</p>" + "<p>نور دل پیغمبرم مزن ای نیزه دار</p>" +
                "<p>پای برهنه می روم عقب قافله</p>" + "<p>سینه ی سوزان دل خون .پای پر ابله</p>" +
                "<p>من یک سه ساله دخترم مزن نیزه ای نیزه</p>" + "<p>نور دل پیغمبرم مزن ای نیزه</p>" +
                "<p>این سر پر خون سربابای من</p>" + "<p>قران تلاوت میکند به تسلی من</p>" +
                "<p>از ترس و وحشت به خدا دل به تاب تب است </p>" + "<p>همدم ومونسم دگر عمه ام زینب است</p>" +
                "<p>من یک سه ساله دخرم مزن ای نیزه دار </p>" + "<p>نور دل پیغمبرم مزن ای نیزه دار</p>" +
                "<p>چشم و چراغ سحر شمع ویرانه ام</p>" + "<p>امدی ای گمشده ی دل ویرانه ام</p>" +
                "<p>وای که سوزد جگرم. جگرم مزن ای نیزه دار</p>" + "<p>به پیش چشم پدرم .پدرم مزن ای نیزه دار</p>" +
                "<p>من یک سه ساله دخترم مزن ای نیزه دار</p>" + "<p>نور دل پیغمبرم مزن ای نیزه دار</p>";

        String            subject     = "من یک سه ساله دخترم مزن ای نیزه دار";
        Const.ContentType contentType = Const.ContentType.NOHEH;
        databaseHelper.contentDAO().insert(new Content("1", "3", "1", answer, content, subject, contentType));

        answer      = "علمدار حسین است،سپهدار حسین است.";
        content     =
                "<p>شیر بی باک علی</p>\n" + "<p>رفت به میدان و</p>\n" + "<p>چوطوفان</p>\n" + "<p>همه شیران و یلان را</p>\n" +
                        "<p>چو پر کاه</p>\n" + "<p>به یک آه</p>\n" + "<p>و ناگاه</p>\n" + "<p>به هم ریخت و شد</p>\n" +
                        "<p>ساقی دلخواه  حسینی</p>\n" + "<p>که همه  دلخوشی</p>\n" + "<p>ماه بنی هاشمیان</p>\n" +
                        "<p>حضرت سلطان ،</p>\n" + "<p>یل میدان بلا بود ،</p>\n" + "<p>چه ماهی؟</p>\n" + "<p>شه پیکار و</p>\n" +
                        "<p>جهاندار و</p>\n" + "<p>سپهدار و</p>\n" + "<p>یل حیدر کرار و</p>\n" + "<p>علم گیر و علمدار و</p>\n" +
                        "<p>زده گردن بسیار و</p>\n" + "<p>به یک حمله ی خونبارو... !</p>\n" + "<p>ابالفضل</p>\n" +
                        "<p>همان مرد دلاور</p>\n" + "<p>پسر حضرت حیدر</p>\n" + "<p>که ز جا کنده در قلعه خیبر</p>\n" +
                        "<p>و نگو ماه</p>\n" + "<p>که از ماه فراتر</p>\n" + "<p>و نگو چشم</p>\n" + "<p>بگو تیزی خنجر</p>\n" +
                        "<p>و نگو دست</p>\n" + "<p>بگو زور دو لشگر</p>\n" + "<p>که شده ذکر لب</p>\n" +
                        "<p>قاسم و عون و علی اکبر ، همه ی عشق برادر،</p>\n" + "<p>پسر شیر عرب،</p>\n" + "<p>دشمن شب ،</p>\n" +
                        "<p>عاشق خورشید نسب ،</p>\n" + "<p>وقت غضب</p>\n" + "<p>قبضه ی شمشیر به کف ،</p>\n" + "<p>وقت سخن</p>\n" +
                        "<p>داشت به لب نقل و رطب</p>\n" + "<p>حضرت زینب همه جا بود</p>\n" + "<p>دعاگوی ابالفضل علمدار؛</p>\n" +
                        "<p>همانی که زده</p>\n" + "<p>از میسره تامیمنه</p>\n" + "<p>او یک تنه</p>\n" +
                        "<p>به به   به چنین هیمنه</p>\n" + "<p>ای آینه بنگر به تن او،</p>\n" + "<p>دم شب شکن او،</p>\n" +
                        "<p>به برق بدن او ،</p>\n" + "<p>به لحن سخن او</p>\n" + "<p>و به خونی که شده</p>\n" +
                        "<p>رود فرات دهن او</p>\n" + "<p>و شده جوشن</p>\n" + "<p>صد پاره ی او هم کفن او</p>\n" +
                        "<p>و قسم می خورم اصلأ</p>\n" + "<p>به همان نور جبینش</p>\n" + "<p>و به آن برق نگینش</p>\n" +
                        "<p>شق القمر و قطع یمینش</p>\n" + "<p>و به باور</p>\n" + "<p>و یقینش</p>\n" + "<p>که شده قوت دینش</p>\n" +
                        "<p>و به آن خنده ی</p>\n" + "<p>ناز نمکینش</p>\n" + "<p>و قسم می خورم اصلأ</p>\n" +
                        "<p>به همان زخم به ابروش،</p>\n" + "<p>به بازوش ،</p>\n" + "<p>به آن عطر خوش</p>\n" +
                        "<p>جوشن و گیسوش که مدهوش شده</p>\n" + "<p>عالمی از بوش</p>\n" + "<p>به ابرو و به بازو و</p>\n" +
                        "<p>به گیسو و بر و روی</p>\n" + "<p>ابالفضل علمدار قسم</p>\n" + "<p>ماه محرم و غمش</p>\n" +
                        "<p>دلخوشی ماست</p>\n" + "<p>و این لطف خود</p>\n" + "<p>حضرت سقاست</p>\n" + "<p>که در سینه ی ما</p>\n" +
                        "<p>شور گل فاطمه برپاست،</p>\n" + "<p>چه زیباست و زهراست</p>\n" + "<p>که در مجلس ما آمده</p>\n" +
                        "<p>از بس که غم</p>\n" + "<p>حضرت عباس در</p>\n" + "<p>اینجاست</p>";

        subject     = "بحر طویل حضرت عباس (ع)";
        contentType = Const.ContentType.NOHEH;
        databaseHelper.contentDAO().insert(new Content("2", "9", "1", answer, content, subject, contentType));

        sessionManager.setFirstTimeLaunch(false);
    }
}