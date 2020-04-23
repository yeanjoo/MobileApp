package hitesh.asimplegame;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

public class QuizDBOpenHelper extends SQLiteOpenHelper {
    private static final String TAG = "QuizDBOpenHelper";

//    private static int DATABASE_RANDOM = 1;
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "mathsone";
    // tasks table name
    private static final String TABLE_QUEST = "quest";
<<<<<<< Updated upstream
=======
    private static final String TABLE_VOICE = "voicequest";
    private static final String TABLE_SCORE = "score";

>>>>>>> Stashed changes
    // tasks Table Columns names
    private static final String KEY_ID = "qid";
    private static final String KEY_QUES = "question";
    private static final String KEY_ANSWER = "answer";
    private static final String KEY_OPTA = "opta";
    private static final String KEY_OPTB = "optb";
    private static final String KEY_OPTC = "optc";
<<<<<<< Updated upstream

=======
    private static final String KEY_SCORE = "keyScore";
    private static int size = 5;
>>>>>>> Stashed changes

    private SQLiteDatabase database;

    private int level;

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
<<<<<<< Updated upstream
        db.execSQL(sql);
//        addQuestion(levelActivity.getLevel());
        addQuestion();
        // db.close();
=======

        String voice_sql = "CREATE TABLE IF NOT EXISTS " + TABLE_VOICE + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
                + " TEXT, " + KEY_ANSWER + " TEXT )";

        String score_sql = "CREATE TABLE IF NOT EXISTS " + TABLE_SCORE + " ( "
                + KEY_ID + " TEXT, " + KEY_SCORE + " INTEGER " + " )";

        db.execSQL(sql);
        db.execSQL(voice_sql);
        db.execSQL(score_sql);
        //초기화 함수
        addVoiceQuestion();
        addQuestion();
        addScore();
//        db.close();
        //        addQuestion(levelActivity.getLevel());

>>>>>>> Stashed changes
    }

/*    private void addQuestion() {
        Question q1 = new Question("5+2 = ?", "7", "8", "6", "7");
        addQuestion(q1);
        Question q2 = new Question("2+18 = ?", "18", "19", "20", "20");
        addQuestion(q2);
        Question q3 = new Question("10-3 = ?", "6", "7", "8", "7");
        addQuestion(q3);
        Question q4 = new Question("5+7 = ?", "12", "13", "14", "12");
        addQuestion(q4);
        Question q5 = new Question("3-1 = ?", "1", "3", "2", "2");
        addQuestion(q5);
        Question q6 = new Question("0+1 = ?", "1", "0", "10", "1");
        addQuestion(q6);
        Question q7 = new Question("9-9 = ?", "0", "9", "1", "0");
        addQuestion(q7);
        Question q8 = new Question("3+6 = ?", "8", "7", "9", "9");
        addQuestion(q8);
        Question q9 = new Question("1+5 = ?", "6", "7", "5", "6");
        addQuestion(q9);
        Question q10 = new Question("7-5 = ?", "3", "2", "6", "2");
        addQuestion(q10);
        Question q11 = new Question("7-2 = ?", "7", "6", "5", "5");
        addQuestion(q11);
        Question q12 = new Question("3+5 = ?", "8", "7", "5", "8");
        addQuestion(q12);
        Question q13 = new Question("0+6 = ?", "7", "6", "5", "6");
        addQuestion(q13);
        Question q14 = new Question("12-10 = ?", "1", "2", "3", "2");
        addQuestion(q14);
        Question q15 = new Question("12+2 = ?", "14", "15", "16", "14");
        addQuestion(q15);
        Question q16 = new Question("2-1 = ?", "2", "1", "0", "1");
        addQuestion(q16);
        Question q17 = new Question("6-6 = ?", "6", "12", "0", "0");
        addQuestion(q17);
        Question q18 = new Question("5-1 = ?", "4", "3", "2", "4");
        addQuestion(q18);
        Question q19 = new Question("4+2 = ?", "6", "7", "5", "6");
        addQuestion(q19);
        Question q20 = new Question("5+1 = ?", "6", "7", "5", "6");
        addQuestion(q20);
        Question q21 = new Question("5-4 = ?", "5", "4", "1", "1");
        addQuestion(q21);
    }*/

    private void addQuestion() {
        for (int i = 0; i < 10; i++) {
            int questNumA = (int) (Math.random() * 10);
            int questNumB = (int) (Math.random() * 10);
            int questAnswer = 0;
            int questOption = (int) (Math.random() * getLevel());

            int optA = 0, optB = 0, optC = 0;
            int optSelRandom = 0;

            String questionString;
            Question question;

            switch (questOption) {
                case 0: // +
                    questAnswer = questNumA + questNumB;
                    optSelRandom = (int) (Math.random() * 3);

                    questionString = (String.valueOf(questNumA) + " + " + String.valueOf(questNumB) + " = ?");

                    switch (optSelRandom) {
                        case 0:
                            optA = questNumA + questNumB;
                            optB = (int) (Math.random() * 10);
                            optC = (int) (Math.random() * 10);
                            break;
                        case 1:
                            optA = (int) (Math.random() * 10);
                            optB = questNumA + questNumB;
                            optC = (int) (Math.random() * 10);
                            break;
                        case 2:
                            optA = (int) (Math.random() * 10);
                            optB = (int) (Math.random() * 10);
                            optC = questNumA + questNumB;
                            break;
                    }

                    question = new Question(questionString, String.valueOf(optA), String.valueOf(optB), String.valueOf(optC), String.valueOf(questAnswer));
                    addQuestion(question);
                    break;

                case 1: // -
                    questAnswer = questNumA - questNumB;
                    optSelRandom = (int) (Math.random() * 2);

                    questionString = (String.valueOf(questNumA) + " - " + String.valueOf(questNumB) + " = ?");

                    switch (optSelRandom) {
                        case 0:
                            optA = questNumA - questNumB;
                            optB = (int) (Math.random() * 10);
                            optC = (int) (Math.random() * 10);
                            break;
                        case 1:
                            optA = (int) (Math.random() * 10);
                            optB = questNumA - questNumB;
                            optC = (int) (Math.random() * 10);
                            break;
                        case 2:
                            optA = (int) (Math.random() * 10);
                            optB = (int) (Math.random() * 10);
                            optC = questNumA - questNumB;
                            break;
                    }

                    question = new Question(questionString, String.valueOf(optA), String.valueOf(optB), String.valueOf(optC), String.valueOf(questAnswer));
                    addQuestion(question);
                    break;
                case 2: // *
                    questAnswer = questNumA * questNumB;
                    optSelRandom = (int) (Math.random() * 2);

                    questionString = (String.valueOf(questNumA) + " * " + String.valueOf(questNumB) + " = ?");

                    switch (optSelRandom) {
                        case 0:
                            optA = questNumA * questNumB;
                            optB = (int) (Math.random() * 10);
                            optC = (int) (Math.random() * 10);
                            break;
                        case 1:
                            optA = (int) (Math.random() * 10);
                            optB = questNumA * questNumB;
                            optC = (int) (Math.random() * 10);
                            break;
                        case 2:
                            optA = (int) (Math.random() * 10);
                            optB = (int) (Math.random() * 10);
                            optC = questNumA * questNumB;
                            break;
                        default:
                            break;
                    }

                    question = new Question(questionString, String.valueOf(optA), String.valueOf(optB), String.valueOf(optC), String.valueOf(questAnswer));
                    addQuestion(question);
                    break;
//                case 3:
//                    break;
                default:
                    break;
            }
        }
    }
//    public static void setDatabaseRandoming(){
//        if(DATABASE_RANDOM<=1){
//            DATABASE_RANDOM=2;
//        }
//        else{
//            DATABASE_RANDOM=1;
//        }
//    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {      //이건 사용을 안하는데?
        setLevel(0);
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST);      //테이블 생성을 위한 문자열 전달
        // Create tables again
        onCreate(db);
    }

//    @Override
//    public void onDowngrade(SQLiteDatabase db, int oldV, int newV){
//        setLevel(0);
//        // Drop older table if existed
//        db.execSQL("DROP TABLE IF EXISTS "  +TABLE_QUEST);
//        // Create tables again
//        onCreate(db);
//    }

    // Adding new question
    public void addQuestion(Question quest) {

        ContentValues values = new ContentValues();     //ContentResolver가 처리 할 수 있는 값 집합을 저장
        values.put(KEY_QUES, quest.getQUESTION());
        values.put(KEY_ANSWER, quest.getANSWER());
        values.put(KEY_OPTA, quest.getOPTA());
        values.put(KEY_OPTB, quest.getOPTB());
        values.put(KEY_OPTC, quest.getOPTC());
        // Inserting Row
        database.insert(TABLE_QUEST, null, values);
    }

<<<<<<< Updated upstream
    public int getLevel(){
        return level;
    }

    public void setLevel(int lv){
        level = lv;
=======
    //<===========VOICE 문제 관련 DB처리 (SQLITE)======================
    //생성자
    private void addVoiceQuestion() {
        VoiceQuestion v1 = new VoiceQuestion("4 더하기 5 빼기 3은 무엇일까요?", "6");
        VoiceQuestion v2 = new VoiceQuestion("3 곱하기 7 더하기 3은 무엇일까요?", "24");
        VoiceQuestion v3 = new VoiceQuestion("15 빼기 4 곱하기 3은 무엇일까요?", "3");
        VoiceQuestion v4 = new VoiceQuestion("4 나누기 2 더하기 5은 무엇일까요?", "7");
        VoiceQuestion v5 = new VoiceQuestion("5더하기 5은 무엇일까요?", "10");
        addVoiceQuestion(v1);
        addVoiceQuestion(v2);
        addVoiceQuestion(v3);
        addVoiceQuestion(v4);
        addVoiceQuestion(v5);
    }

    //DB 적용
    public void addVoiceQuestion(VoiceQuestion quest) {
        ContentValues values = new ContentValues();     //ContentResolver가 처리 할 수 있는 값 집합을 저장
        values.put(KEY_QUES, quest.getQUESTION());
        values.put(KEY_ANSWER, quest.getANSWER());
        // Inserting Row
        database.insert(TABLE_VOICE, null, values);
    }

    //voice 문제 반환 (sqlite)
    public List<VoiceQuestion> getAllVoiceQuestions() {
        List<VoiceQuestion> quesList = new ArrayList<VoiceQuestion>();
        String selectQuery = "SELECT  * FROM " + TABLE_VOICE;

        database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                VoiceQuestion question = new VoiceQuestion();
                question.setID(cursor.getInt(0));
                question.setQUESTION(cursor.getString(1));
                question.setANSWER(cursor.getString(2));

                quesList.add(question);
            } while (cursor.moveToNext());
        }
        return quesList;
    }
    //<========================= 스코어 저장=========================
    public List<Score> getAllScore() {
        List<Score> quesList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_SCORE +" ORDER BY "+KEY_SCORE+" DESC"; //내림차순정렬
        database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Score score = new Score();
                score.setUser(cursor.getString(0));
                score.setScore(cursor.getInt(1));
                quesList.add(score);
            } while (cursor.moveToNext());
        }
        return quesList;
    }
    private void addScore(){
        Score score = new Score("민트초코 존맛탱",10);
        addScore(score);
        Score score1 = new Score("파인애플피자 존맛탱",9);
        addScore(score1);
    }

    public void addScore(Score score) {
        ContentValues values = new ContentValues();     //ContentResolver가 처리 할 수 있는 값 집합을 저장
        values.put(KEY_ID, score.getUser());
        values.put(KEY_SCORE, score.getScore());
        // Inserting Row
        database.insert(TABLE_SCORE, null, values); //외부 클래스에서 직접 사용시 ERROR, 객체 생성 후 사용
>>>>>>> Stashed changes
    }
    //========================================================================>//

    public List<Question> getAllQuestions(int lv) {
//        getLevel();
        setLevel(lv);
        List<Question> quesList = new ArrayList<Question>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
        database = this.getReadableDatabase();      //이때 onCreate
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
<<<<<<< Updated upstream

=======
        setLevel(0);
        checkFile();
        return quesList;
    }

    public void checkFile() {//왜 있는지 몰라서 따로 빼놓음
>>>>>>> Stashed changes
        String databasePath = "/data/user/0/hitesh.asimplegame/databases";
        File mFile = new File(databasePath);
        if (mFile.exists()) {
            if (mFile.isDirectory()) {
                File[] files = mFile.listFiles();
                for (int i = 0; i < files.length; i++) {
                    if (files[i].delete()) {
                        Log.d(TAG, "==== Foldering File Deleted.");
                    } else {
                        Log.d(TAG, "==== Foldering File Not Deleted.");
                    }
                }
            }

            if (mFile.delete()) {
                Log.d(TAG, "==== File Deleted.");
            } else {
                Log.d(TAG, "==== File Deleted.");
            }
        }
        setLevel(0);

        return quesList;
    }
<<<<<<< Updated upstream


=======
    //외부 write를 위한 get method 필요거 아래로 추가
    public String getDatabaseName() {
        return DATABASE_NAME;
    }
    public String getTableScore() {
        return TABLE_SCORE;
    }
    public String getKeyId(){
        return KEY_ID;
    }
    public String getKeyScore(){
        return KEY_SCORE;
    }
    //======================//
    public int getLevel() {
        return level;
    }

    public void setLevel(int lv) {
        level = lv;
    }
>>>>>>> Stashed changes
}