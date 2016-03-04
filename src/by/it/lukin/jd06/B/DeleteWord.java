package by.it.lukin.jd06.B;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by алексей on 18.02.2016.
 */
public class DeleteWord {
    public static void main(String[] args){
        String s = "У лукоморья дуб зелёный;\n" +
                "Златая цепь на дубе том:\n" +
                "И днём и ночью кот учёный\n" +
                "Всё ходит по цепи кругом;\n" +
                "Идёт направо - песнь заводит,\n" +
                "Налево - сказку говорит.\n" +
                "Там чудеса: там леший бродит,\n" +
                "Русалка на ветвях сидит;\n" +
                "Там на неведомых дорожках\n" +
                "Следы невиданных зверей;\n" +
                "Избушка там на курьих ножках\n" +
                "Стоит без окон, без дверей;\n" +
                "Там лес и дол видений полны;\n" +
                "Там о заре прихлынут волны\n" +
                "На брег песчаный и пустой,\n" +
                "И тридцать витязей прекрасных\n" +
                "Чредой из вод выходят ясных,\n" +
                "И с ними дядька их морской;\n" +
                "Там королевич мимоходом\n" +
                "Пленяет грозного царя;\n" +
                "Там в облаках перед народом\n" +
                "Через леса, через моря\n" +
                "Колдун несёт богатыря;\n" +
                "В темнице там царевна тужит,\n" +
                "А бурый волк ей верно служит;\n" +
                "Там ступа с Бабою Ягой\n" +
                "Идёт, бредёт сама собой,\n" +
                "Там царь Кащей над златом чахнет;\n" +
                "Там русский дух... там Русью пахнет!\n" +
                "И там я был, и мёд я пил;\n" +
                "У моря видел дуб зелёный;\n" +
                "Под ним сидел, и кот учёный\n" +
                "Свои мне сказки говорил.";

        String regexp = "\\b((?=[а-яА-ЯйЙ])[^АаЕеЁёИиОоУуыЭЭЮюЯя][а-яА-ЯйЙ]{4})\\b";
        Pattern p = Pattern.compile(regexp);
        Matcher m = p.matcher(s);
        String s2 = m.replaceAll("");
        System.out.println(s2);
    }
}