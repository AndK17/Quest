package dev.andk.quest;

import android.view.View;

public class Story {

    private Situation start_story;
    public Situation current_situation;
    Player character;
    MainActivity m;
    Story(MainActivity ma) {
        m = ma;
        character = ma.character;
        start_story = new Situation(
                "Сложный выбор",
                "Отлично. Сначала нужно решить сколько мерная будет игра\n"
                        + "(1)2D\n"
                        + "(2)3D",
                2, 0, 0, 0);
        start_story.direction[0] = new Situation(
                "Жанр",
                "Теперь надо определиться с жанром\n"
                        + "(1)Платформер\n"
                        + "(2)Раннер\n"
                        + "(3)Top Down Shooter",
                3, 0, 0, 0);
        start_story.direction[1] = new Situation(
                "Жанр",
                "Теперь надо определиться с жанром\n"
                        + "(1)Шутер\n"
                        + "(2)Виживание",
                2, 0, 0, 0);
        start_story.direction[0].direction[0] = start_story.direction[0].direction[1] = start_story.direction[0].direction[2]
                = start_story.direction[1].direction[0] = start_story.direction[1].direction[1] = new Situation(
                "Реклама",
                "Сколько вложить в рекламу\n"
                        +"(1)" + character.M/10 + "$\n"
                        +"(2)" + character.M/4 + "$\n"
                        +"(3)" + character.M/2 + "$",
                3, 0, 0, 0);
        start_story.direction[0].direction[0].direction[0] = start_story.direction[0].direction[1].direction[0] = start_story.direction[0].direction[2].direction[0]
                = start_story.direction[1].direction[0].direction[0] = start_story.direction[1].direction[1].direction[0] = new Situation(
                "Релиз",
                "Из-за слишком маленького бюджета на рекламу игра провалиласть.\n"
                        +"(1) Расстроиться и бросить разработку\n"
                        +"(2) Забить на провал и попробовать еще раз",
                2, 50, -character.M/10, -10);
        start_story.direction[1].direction[1].direction[0].direction[0] = new Situation(
                "Дипрессия",
                "Вы очень расстроились из-за провала. И закупившись едой, вы залипли в сериалы и игры на неделю. "
                        +"После чего всетаки решили вернуться к разработке и уже работаете над новым проектом, которой точно выстрелит...",
                0, -character.F/5, -150, -30);
        start_story.direction[1].direction[1].direction[0].direction[1] = new Situation(
                "Вторая попытка",
                "Забив на провал, вы начинаете работу над новой игрой, которую вскоре выпустете...",
                0, -character.F/5, -150, -30);
        if (Math.random() > 0.5) {
            start_story.direction[0].direction[0].direction[1] = start_story.direction[0].direction[1].direction[1] = start_story.direction[0].direction[2].direction[1]
                    = start_story.direction[1].direction[0].direction[1] = start_story.direction[1].direction[1].direction[1] = new Situation(
                    "Релиз",
                    "Из-за небольшого количества рекламы, игра не получила большой популяронсти",
                    0, 300, character.M / 3 - character.M / 4, 5);
        }else {
            start_story.direction[0].direction[0].direction[1] = start_story.direction[0].direction[1].direction[1] = start_story.direction[0].direction[2].direction[1]
                    = start_story.direction[1].direction[0].direction[1] = start_story.direction[1].direction[1].direction[1] = new Situation(
                    "Релиз",
                    "Отличная новость! Игра стала довольно популярна",
                    0, 1000, character.M * 3 / 2 - character.M / 4, 10);
        }
        start_story.direction[0].direction[0].direction[2] = start_story.direction[0].direction[1].direction[2] = start_story.direction[0].direction[2].direction[2]
                = start_story.direction[1].direction[0].direction[2] = start_story.direction[1].direction[1].direction[2] = new Situation(
                "Релиз",
                "Игра завирусилась и стала очень популярна",
                0, 50000, character.M * 10 - character.M / 4, 25);
        current_situation = start_story;
    }

    public void go(int num) {
        if (num <= current_situation.direction.length) {
            current_situation = current_situation.direction[num - 1];
            for (int i = 0; i < 3; i++) {
                if (i < current_situation.direction.length) {
                    m.choices[i].setVisibility(View.VISIBLE);
                    m.choices[i].setText(Integer.toString(i + 1));
                } else {
                    m.choices[i].setVisibility(View.INVISIBLE);
                }
            }
        }else
            m.console.setText("Вы можете выбирать из "
                    + current_situation.direction.length + " вариантов");
        m.resource.setText("Фанаты:" + character.F + "    Баланс:" + character.M + "$    Репутация:" + character.R);
    }

    public boolean isEnd() {
        return current_situation.direction.length == 0;
    }
}
