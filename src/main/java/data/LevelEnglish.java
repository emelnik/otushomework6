package data;

public enum LevelEnglish {

    BEGINNER("Начальный уровень (Beginner)"),
    ELEMENTARY("Элементарный уровень (Elementary)"),
    PRE_INTERMEDIATE("Ниже среднего (Pre-Intermediate)"),
    INTERMEDIATE("Средний (Intermediate"),
    UPPER_INTERMEDIATE("Выше среднего (Upper Intermediate)"),
    ADVANCED("Продвинутый (Advanced)"),
    MASTERY("Супер продвинутый (Mastery)");

    private String levelEnglish;

    LevelEnglish(String levelEnglish){
        this.levelEnglish = levelEnglish;
    }

    public String getLevelEnglish(){
        return levelEnglish;
    }

}
