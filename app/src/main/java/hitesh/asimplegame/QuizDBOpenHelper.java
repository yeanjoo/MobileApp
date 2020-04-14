package hitesh.asimplegame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

public class QuizDBOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;      //이거 바꾸니까 문제 바뀜..
    // Database Name
    private static final String DATABASE_NAME = "mathsone";
    // tasks table name
    private static final String TABLE_QUEST = "quest";
    // tasks Table Columns names
    private static final String KEY_ID = "qid";
    private static final String KEY_QUES = "question";
    private static final String KEY_ANSWER = "answer"; // correct option
    private static final String KEY_OPTA = "opta"; // option a
    private static final String KEY_OPTB = "optb"; // option b
    private static final String KEY_OPTC = "optc"; // option c

    private SQLiteDatabase database;

    public QuizDBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {       //디비를 쓰긴함?
        database = db;
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUEST + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
                + " TEXT, " + KEY_ANSWER + " TEXT, " + KEY_OPTA + " TEXT, "
                + KEY_OPTB + " TEXT, " + KEY_OPTC + " TEXT)";
        db.execSQL(sql);
//        addQuestion();
        addQuestion(1);
        // db.close();
    }

//    private void addQuestion() {        //왜 굳이 문제를 만들어 놓은걸까? ->이걸 랜덤으로 만들어서 단계 무제한으로 하면?
//        Question q1 = new Question("5+2 = ?", "7", "8", "6", "7");
//        addQuestion(q1);
//        Question q2 = new Question("2+18 = ?", "18", "19", "20", "20");
//        addQuestion(q2);
//        Question q3 = new Question("10-3 = ?", "6", "7", "8", "7");
//        addQuestion(q3);
//        Question q4 = new Question("5+7 = ?", "12", "13", "14", "12");
//        addQuestion(q4);
//        Question q5 = new Question("3-1 = ?", "1", "3", "2", "2");
//        addQuestion(q5);
//        Question q6 = new Question("0+1 = ?", "1", "0", "10", "1");
//        addQuestion(q6);
//        Question q7 = new Question("9-9 = ?", "0", "9", "1", "0");
//        addQuestion(q7);
//        Question q8 = new Question("3+6 = ?", "8", "7", "9", "9");
//        addQuestion(q8);
//        Question q9 = new Question("1+5 = ?", "6", "7", "5", "6");
//        addQuestion(q9);
//        Question q10 = new Question("7-5 = ?", "3", "2", "6", "2");
//        addQuestion(q10);
//        Question q11 = new Question("7-2 = ?", "7", "6", "5", "5");
//        addQuestion(q11);
//        Question q12 = new Question("3+5 = ?", "8", "7", "5", "8");
//        addQuestion(q12);
//        Question q13 = new Question("0+6 = ?", "7", "6", "5", "6");
//        addQuestion(q13);
//        Question q14 = new Question("12-10 = ?", "1", "2", "3", "2");
//        addQuestion(q14);
//        Question q15 = new Question("12+2 = ?", "14", "15", "16", "14");
//        addQuestion(q15);
//        Question q16 = new Question("2-1 = ?", "2", "1", "0", "1");
//        addQuestion(q16);
//        Question q17 = new Question("6-6 = ?", "6", "12", "0", "0");
//        addQuestion(q17);
//        Question q18 = new Question("5-1 = ?", "4", "3", "2", "4");
//        addQuestion(q18);
//        Question q19 = new Question("4+2 = ?", "6", "7", "5", "6");
//        addQuestion(q19);
//        Question q20 = new Question("5+1 = ?", "6", "7", "5", "6");
//        addQuestion(q20);
//        Question q21 = new Question("5-4 = ?", "5", "4", "1", "1");
//        addQuestion(q21);
//    }



    public void addQuestion(int level) {
        int a;
        int b;
        int op;
        int answer;
        String quiz;
        ArrayList ans = new ArrayList();



        if (level == 1) {

            for (int i = 0; i < 20; i++) {
                answer = 0;
                ans.removeAll(ans);
                a = (int)(Math.random() * 10);
                b = (int)(Math.random() * 10);
                op = (int)(Math.random() * 2);

                if (op == 0) {       //덧셈
                    answer = a + b;
                    ans.add(answer);
                    ans.add((int)(Math.random()*20));
                    ans.add((int)(Math.random()*20));

                    Collections.shuffle(ans);

                    quiz = String.valueOf(a) + " + " + String.valueOf(b) + " =  ?";
                    Question question = new Question(quiz, String.valueOf(ans.get(0)), String.valueOf(ans.get(1)), String.valueOf(ans.get(2)), String.valueOf(answer));
                    addQuestion(question);
                }
                if (op == 1) {       //뺄셈
                    answer = a-b;
                    ans.add(answer);
                    ans.add((int)(Math.random()*10));
                    ans.add((int)(Math.random()*10));

                    Collections.shuffle(ans);

                    quiz = String.valueOf(a) + " - " + String.valueOf(b) + " =  ?";
                    Question quest = new Question(quiz, String.valueOf(ans.get(0)), String.valueOf(ans.get(1)), String.valueOf(ans.get(2)), String.valueOf(answer));

                    addQuestion(quest);
                }
            }
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {      //이건 사용을 안하는데?
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST);      //테이블 생성을 위한 문자열 전달
        // Create tables again
        onCreate(db);
    }

    // Adding new question
    public void addQuestion(Question quest) {
        // SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();     //ContentResolver가 처리 할 수 있는 값 집합을 저장
        values.put(KEY_QUES, quest.getQUESTION());
        values.put(KEY_ANSWER, quest.getANSWER());
        values.put(KEY_OPTA, quest.getOPTA());
        values.put(KEY_OPTB, quest.getOPTB());
        values.put(KEY_OPTC, quest.getOPTC());

        // Inserting Row
        database.insert(TABLE_QUEST, null, values);
    }

    public List<Question> getAllQuestions() {
        List<Question> quesList = new ArrayList<Question>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
        database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);       //rawQuery : 데이터베이스에 저장된 데이터를 찾아서 가져옴       //커서는 행단위 참조
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {     //moveToFirst : 가장 첫번째 행 선택
            do {
                Question quest = new Question();
                quest.setID(cursor.getInt(0));      //선택된 행의 첫번째 열
                quest.setQUESTION(cursor.getString(1));
                quest.setANSWER(cursor.getString(2));
                quest.setOPTA(cursor.getString(3));
                quest.setOPTB(cursor.getString(4));
                quest.setOPTC(cursor.getString(5));

                quesList.add(quest);
            } while (cursor.moveToNext());      //moveToNext : 순서상으로 다음 행 선택
        }
        // return quest list
        return quesList;
    }
}
