package com.lpf.oneminute.util;

import java.util.Random;

/**
 * Created by liupengfei on 17/3/21 08:58.
 */

public class DailyWordUtil {

    private static String[] dayWords = new String[]{

            "The proper function of man is to live,but not to exist.",
            "While there is life there is hope.",
            "If you smile when one is around,you really mean it.",
            "I am a slow walker,but I never walk backwards.",
            "A beautiful form is better than a beautiful face;a beautiful behavior than a beautiful form.",
            "A man is not old as long as he is seeking something. A man is not old until regrets take the place of dreams. ",
            "Wonders are many,and nothing is more wonderful then man.",
            "The worst bankrupt is the person who lost his enthusiasm.",
            "I can make it through the rain. I can stand up once again on my own.",
            "Never, never, never, never give up",
            "There is a time to speak and a time to be silent.",
            "A thousand-li journey is started by taking the first step.",
            "It's great to be great , but it''s greater to be human. ---W. Rogers",
            "We can only love what we know and we can never know completely what we do not love.",
            "Cease to struggle and you cease to live.（Thomas Carlyle）",
            "A strong man will struggle with the storms of fate.(Thomas Addison)",
            "Living without an aim is like sailing without a compass.（John Ruskin）",
            "Live a noble and honest life. Reviving past times in your old age will help you to enjoy your life again.",
            "Accept what was and what is, and you’ll have more positive energy to pursue what will be.",
            "Behind every successful man there's a lot u unsuccessful years. (Bob Brown)",
            "Enrich your life today,. yesterday is history.tomorrow is mystery.",
            "The secret of success is constancy to purpose.",
            "Between two stools one falls to the ground.",
            "You have to believe in yourself. That's the secret of success. (Charles Chaplin)",
            "Success grows out of struggles to overcome difficulties.",
            "The dictionary is the only place where success comes before work.",
            "There are no shortcuts to any place worth going.",
            "You're uinique, nothing can replace you.",
            "Great works are performed not by strengh, but by perseverance.(Samuel Johnson, British writer and critic)",
            "Until you make peace with who you are, you’ll never be content with what you have.",
            "people's backs and heads .(F.W .Nietzsche , German Philosopher)",
            "Keep trying no matter how hard it seems. it will get easier.",
            "True mastery of any skill takes a lifetime.",
            "The first wealth is health .(Ralph Waldo Emerson , American thinker)",
            "Where there is life, there is hope.",
            "What makes life dreary is the want of motive.(George Eliot)",
            "Shallow men believe in luck.Self-trust is the first secret of success.",
            "I have no secret of success but hard work.",
            "If you fail, don't forget to learn your lesson.",
            "I have nothing to offer but blood, boil, tears and sweat. (Winston Churchill, British politician)",
            "Sweat is the lubricant of success.",
            "A contented mind is the greatest blessing a man can enjoy in this world.",
            "That man is the richest whose pleasure are the cheapest.",
            "You make the failure complete when you stop trying.",
            "If winter comes , can spring be far behind ?( P. B. Shelley , British poet )",
            "Will, work and wait are the pyramidal cornerstones for success.",
            "Success often depends upon knowing how long it will take to succeed.",
            "Think great thoughts and you will be great!",
            "Only those who have the patience to do simple things perfectly ever acquire the skill to do difficult things easily.",
            "Gods determine what you're going to be.(Julius Erving)",
            "A man can succeed at almost anything for which he has unlimited enthusiasm.",
            "The man who has made up his mind to win will never say “impossible”.",
            "The only limit to our realization of tomorrow will be our doubts of today.",
            "Don't aim for success if you want it; just do what you love and believe in, and it will come naturally.",
            "One thorn of experience is worth a whole wilderness of warning.",
            "It never will rain roses.When we want to have more roses we must plant trees.",
            "No man or woman is worth your tears, and the one who is, won't make you cry.",
            "Each man is the architect of his own fate.",
            "Great works are performed not by strength , but by perseverance.",
            "Dare and the world always yields.If it beats you sometimes, dare it again and again and it will succumb.",
            "I think success has no rules, but you can learn a lot from failure.(Jean Kerr)",
            "All time is no time when it is past.",
            "Live well, love lots, and laugh often.",
            "Don't give up and don't give in.",
            "To the world you may be one person, but to one person you may be the world.",
            "To choose time is to save time .( Francis Bacon , British philosopher )",
            "Whatever is worth doing is worth doing well.",
            "There are no secrets to success. It is the result of preparation, hard work, and learning from failure.",
            "A chain is no stronger than its weakest link.",
            "The best preparation for tomorrow is doing your best today.",
            "Take control of your own desting.",
            "Adversity is a good discipline.",
            "In the end, it’s not the years in your life that count. It’s the life in your years.",
            "Great oaks from little acorns grow.",
            "A man can fail many times, but he isn't a failure until he begins to blame somebody else.(J. Burroughs)",
            "A friend without faults will never be found.",
            "If you are doing your best,you will not have to worry about failure.",
            "Don't try so hard, the best things come when you least expect them to.",
            "He is wise that knows when he's well enough.",
            "If there were no clouds, we should not enjoy the sun.",
            "Perseverance can sometimes equal genius in its results.",
            "An aim in life is the only fortune worth finding.(Robert Louis Stevenson)",
            "Experience is the mother of wisdom.",
            "If you don't aim high you will never hit high.",
            "However mean your life is, meet it and live it; do not shun it and call it hard names.",
            "It is the first step that costs.",
            "All that you do, do with your might; things done by halves are never done right.",
            "I succeeded because I willed it; I never hesitated.",
            "Every man is best know to himself.",
            "Labour is often the father of pleasure.",
            "Never underestimate your power to change yourself!",
            "Every noble work is at first impossible.",
            "Whatever happens, happens for a reason.",
            "Winners do what losers don't want to do.",
            "Happiness is a way station between too much and too little.",
            "You cannot improve your past, but you can improve your future. Once time is wasted, life is wasted.",
            "Energy and persistence conquer all things.(Benjamin Franklin)",
            "Try not to become a man of success but rather try to become a man of value. ( A. Einstein)",

    };


    public static String getDayWord(){
        int index = new Random().nextInt(dayWords.length);
        return dayWords[index];
    }
}
