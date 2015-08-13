package com.github.andrdev.easyenglish;

import com.github.andrdev.easyenglish.model.MemoItem;

import java.util.List;

/**
 * Created by taiyokaze on 8/10/15.
 */
public interface Slang {


    String[] slang = {"Let Someone Down",
            "Living End",
            "Like it or Lump it",
            "Make eyes at",
            "Fall for",
            "Оut in the left field",
            "Оuch!",
            "Оnce over",
            "Оn the tip of one's tongue",
            "Оn pins and needles",
            "In the Pink",
            "In the Nick of Time",
            "In the Bucks",
            "In High Gear",
            "In Clover",
            "In Cahoots",
            "In a Quandary",
            "In a Pinch",
            "In a Pig's Eye",
            "Just what the doctor ordered",
            "Jump the track",
            "Jump the gun",
            "Jump out of the skin",
            "Jump on, jump all over",
            "On the Top of the World",
            "On the Wagon",
            "On the Sly",
            "On the Skids",
            "On the Run",
            "On the Rocks",
            "To sort something out",
            "Smashing",
            "Knackered",
            "Posh",
            "'m easy",
            "Fancy something",
            "сheeky",
            "Cheesed off",
            "Mula",
            "Mug",
            "Mosey along",
            "Mind-blowing",
            "Mellow out",
            "Mega",
            "Man",
            "Backfire",
            "Backet Case",
            "Back up",
            "Back out",
            "Back off",
            "Back in the Sun",
            "Back down",
            "Use one's Beam",
            "Uptight",
            "Ups and Down",
            "Upbeat",
            "Up to the Chin",
            "Up to Snuff",
            "Up to Scratch (usually used with a negative)",
            "Up the Tree",
            "Up in the Clouds",
            "Kid",
            "Kicky",
            "Kick up the Dust (raise a dust)",
            "In your face",
            "Nail",
            "No offense",
            "Kick rocks",
            "Junk",
            "Hot",
            "Kick up a Quarrel",
            "Kick up a Fuss",
            "Over the hill",
            "Out to lunch",
            "Kiss Ass",
            "Run for it",
            "Rub out",
            "Rub it in",
            "Rip off",
            "He-man",
            "Helter-skelter",
            "Hella good",
            "Go ape over",
            "Give up the ghost",
            "Green with envy",
            "Drag one's Feet",
            "Pick Someone up",
            "Penny Pincher",
            "Peanuts",
            "Ease up",
            "Easy-going",
            "Ace!",
            "A screw loose",
            "Carry the Day",
            "Carry away",
            "Gaga",
            "Look down on",
            "Long Shot",
            "Long Hair",
            "Piss off",
            "Up Front"};

    String[] slang_translate = {"подводить кого-либо, когда ты кого-то подставил",
            "обалденно, очень круто",
            "хочешь, не хочешь",
            "когда кто-то стреляет глазами, строит глазки",
            "Чувство, которое описывает влюбленного человека; влипнуть, втрескаться, втюриться, влюбиться.",
            "когда кто-то что-то делает или говорит не в тему, не по сути. Не из той оперы, не в ту степь.",
            "(восклицание) — Ой! Ай! ауч!",
            "Быстрый (но далеко не безразличный) взгляд на кого-либо или что-либо.",
            "когда что-то очень хочешь вспомнить, но никак не получается, как будто вертится на кончике языка; на языке крутится, а вспомнить не могу",
            "быть в состоянии готовности или в сильных переживаниях из-за какого-то происходящего события или наближающегося. Быть как на иголках.",
            "Человек, который в хорошем здоровье, как эмоционально так и физически.",
            "Как раз в нужно время, во время.",
            "Человек, который имеет много денег. Так говорят, когда хотят сказать, что он при деньгах.",
            "Что-то, что находится в полном разгаре.",
            "Быть в хорошем положении или находиться в хорошей ситуации, часто говорят - как сыр в масле.",
            "Когда несколько человек или группа людей действует сообща, делает что-то вместе",
            "быть, находиться в затруднительном положении, когда что-то сделать очень сложно.",
            "делать что-то только в крайнем случае, при большой необходимости, последняя возможность.",
            "что-то сделанное с большим трудом трудом или едва сделанное. Вобщем, было не просто это сделать.",
            "когда что-то ну просто идеально подходит, мы говорим \"То, что доктор прописал\"",
            "Перескакивать, перепрыгивать, что-то быстро менять на ходу.",
            "Когда что-то было Начато раньше времени, или другими словами сделать фальшстарт",
            "дословные перевод звучит так: Выпрыгивать (от страха) из собственной кожи, а мы в таких случаях подрозумеваем \"душа в пятки ушла\", тоесть быть очень напуганным.",
            "человек или действие, которое обозначает наезжать, наскакивать (в смысле: ругаться, обвинять).",
            "когда человек находится в поднесенном духе или на седьмом небе",
            "человек, который бросил пить, который находится сейчас в завязке",
            "когда кто-то cделал что-то тайно; сделанное втайне от кого-то",
            "Делать что-то, что будет обреченно на провал или совсем не будет иметь успеха.",
            "когда ты делаешь что-то на ходу или по пути.",
            "Быть на мели. Когда у вас нет денег и вы хотите это подчеркнуть, тогда так говорят, я на мели.",
            "утрясать проблемы, неприятности",
            "прилагательное, обозначающее что-то великолепное, превосходное",
            "прилагательное, которое обозначает кого-то очень-очень уставшего",
            "что-то, что является дорогим, что-то богатое или что имеет шикарный вид",
            "мне всё равно, это не имеет для меня значения.",
            "Когда тебе что-то нравится и вы отдаете этому свое предпочтение. ",
            "так говорятся о том, кто любит сказать что-то осторое или о нахале, который хочет съязвить.",
            "Кто-то, кто очень разозлился из-за чего-либо",
            "Это слово обозначает грубое слово для денег - \"бабло\"",
            "грубое слово для обозначения лица - \"рожа\"",
            "идти, ходить медленно, не спеша, обыденно",
            "Прилагательное, которое иписывает что-то потрясное, фантастическое, шокирующее",
            "Успокоиться, угомониться, перестать вести себя очень не спокойно и активно.",
            "Слово, обозначающее что-то большое и крупное",
            "Дружеское обращение к лицу мужского пола, которое, как правило, близкое по значению к словам \"приятель\", \"дружбан\", и т.п.",
            "Ударить рикошетом. Когда что-то ударилось об какой-то предмет, а потом отскочил и ударил кого-то.",
            "Беспомощный человек, человек, который ничего не может сделать сам.",
            "1. двигаться назад, возвращаться куда-то ; 2. помогать, поддерживать или собираться это сделать; 3. подстраховывать (в игре)",
            "Когда кто-то что-то пообещал и не сдержал данного слова.",
            "1. прекращать пороть чушь, затыкаться; 2. говорить тише и медленнее",
            "Почивать на лаврах. Когда ты чего-то достиг и успокоился на достигнутом.",
            "Отступаться, отказываться",
            "Шевелить мозгами. Когда кто-то тормозит или тупит, ему нужно включить мозги, пошевелить мозгами.",
            "1. встревоженный, взволнованный; 2. напряжённый, скованный",
            "Взлеты и падения. ",
            "человек с радостным взглядом на жизнь, который смотрит на все оптимистично.",
            "По горло. Когда что-то уже настолько першло линию, что уже нет терпения.",
            "Удовлетворительно; на должной высоте",
            "На должной высоте",
            "Безвыходное положение. Один из тех случеаев, когда вы просто не знаете, что делать.",
            "Витать в облаках. Описывает состояние, когда вы не можете понять, что происходит, или когда к вам говорят, а слышите, но не понимаете. Обычно тогда, когда человек влюбился.",
            "Слово, которое употребляется, когда кто-то хочет над кем-то подшутить. Еще также может быть перевод подтрунивать, подкалывать или говорить что-то не серьёзно, в шутку.",
            "Модный, нарядный, восхитительный, стильный. Относится к человеку с хорошим вкусом одежде и не только, но и с хорошим вкусов в стиле.",
            "Поднимать шум, шумиху. Кипиш. Делать что-то, что привлекает внимание из-за большого шума.",
            "Восклицание, показывающее ваше неуважение к собеседнику, презрение к нему или к тому, что он сказал; используется для придания грубости ответу. ",
            "1) сделать что-то очень точно, с большой аккуратностью; 2) прибивать гвоздями.",
            "без обид",
            "отвали, иди отсюда",
            "1) хлам 2) героин",
            "1) привлекательный, красивый, симпатичный и т.п. 2) что-то украденное, незаконно нажитое",
            "Устраивать сцену; устраивать скандал",
            "Поднимать шум, создавать шумиху",
            "Уже не тот, т.е. не тот, что был раньше (годы берут свое)",
            "Tормознутый, отмороженный",
            "Подлизываться",
            "Спасаться, спасать жизнь бегством",
            "В пух и прах, убить",
            "Постоянно напоминать, все вермя напоминать",
            "Грубить, быть грубым, вести себя не воспитанно ",
            "Hастоящий мужчина, силач, мачо",
            "Cломя голову, очень быстро",
            "Oчень хорошо, просто отлично, бомбезно, круто",
            "Дуреть, с ума сходить",
            "Отбрасывать копыта, умереть, уйти на тот свет.",
            "Позеленеть от зависти",
            "Работать медленно, с неохотой, не желать делать то, что делаешь",
            "Арестовать, брать под арест, бросить за решетку.",
            "Скряга, скупердяй, жаднюга, жид, жадина",
            "Гроши, деньги, бабло, очень мало денег",
            "Замедлить, приостановить ",
            "Общительный, оммуникабельный",
            "Прекрасно, здорово, круто, употр. вместо \"excellent, wonderful\" ",
            "Не все дома, не в своем уме, \"крыша поехала\", с ума сошла",
            "Одержать верх, победить, быть победителем.",
            "1. Возбуждать; 2. Кайфовать",
            "1. Слабоумный, глупый; 2. Сумасшедший; человек, страдающий старческим слабоумием",
            "Смотреть свысока, быть высокомерным, проявлять гордость",
            "Мало шансов на успех, почти нет шансов на хороший результат",
            "Интеллектуал, умник",
            "Доставать, надоедать до предела, досождать, становиться не интересным",
            "1. Заранее; авансом; наперед 2. Откровенно"};

}