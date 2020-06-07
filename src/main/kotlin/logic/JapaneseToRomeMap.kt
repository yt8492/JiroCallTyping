package logic

val JapaneseToRomeMap = mapOf(
    "ァ" to listOf("la", "xa"),
    "ア" to listOf("a"),
    "ィ" to listOf("li", "xi"),
    "イ" to listOf("i"),
    "ゥ" to listOf("lu", "xu"),
    "ウ" to listOf("u", "wu", "whu"),
    "ェ" to listOf("le", "xe"),
    "エ" to listOf("e"),
    "ォ" to listOf("lo", "xo"),
    "オ" to listOf("o"),
    "カ" to listOf("ka", "ca"),
    "ガ" to listOf("ga"),
    "キ" to listOf("ki"),
    "ギ" to listOf("gi"),
    "ク" to listOf("ku", "cu"),
    "グ" to listOf("gu"),
    "ケ" to listOf("ke"),
    "ゲ" to listOf("ge"),
    "コ" to listOf("ko", "co"),
    "ゴ" to listOf("go"),
    "サ" to listOf("sa"),
    "ザ" to listOf("za"),
    "シ" to listOf("si", "shi", "ci"),
    "ジ" to listOf("zi", "ji"),
    "ス" to listOf("su"),
    "ズ" to listOf("zu"),
    "セ" to listOf("se"),
    "ゼ" to listOf("ze"),
    "ソ" to listOf("so"),
    "ゾ" to listOf("zo"),
    "タ" to listOf("ta"),
    "ダ" to listOf("da"),
    "チ" to listOf("ti", "chi"),
    "ヂ" to listOf("di"),
    "ッ" to listOf("ltu", "xtu"),
    "ツ" to listOf("tu", "tsu"),
    "ヅ" to listOf("du"),
    "テ" to listOf("te"),
    "デ" to listOf("de"),
    "ト" to listOf("to"),
    "ド" to listOf("do"),
    "ナ" to listOf("na"),
    "ニ" to listOf("ni"),
    "ヌ" to listOf("nu"),
    "ネ" to listOf("ne"),
    "ノ" to listOf("no"),
    "ハ" to listOf("ha"),
    "バ" to listOf("ba"),
    "パ" to listOf("pa"),
    "ヒ" to listOf("hi"),
    "ビ" to listOf("bi"),
    "ピ" to listOf("pi"),
    "フ" to listOf("hu", "fu"),
    "ブ" to listOf("bu"),
    "プ" to listOf("pu"),
    "ヘ" to listOf("he"),
    "ベ" to listOf("be"),
    "ペ" to listOf("pe"),
    "ホ" to listOf("ho"),
    "ボ" to listOf("bo"),
    "ポ" to listOf("po"),
    "マ" to listOf("ma"),
    "ミ" to listOf("mi"),
    "ム" to listOf("mu"),
    "メ" to listOf("me"),
    "モ" to listOf("mo"),
    "ャ" to listOf("lya", "xya"),
    "ヤ" to listOf("ya"),
    "ュ" to listOf("lyu", "xyu"),
    "ユ" to listOf("yu"),
    "ョ" to listOf("lyo", "xyo"),
    "ヨ" to listOf("yo"),
    "ラ" to listOf("ra"),
    "リ" to listOf("ri"),
    "ル" to listOf("ru"),
    "レ" to listOf("re"),
    "ロ" to listOf("ro"),
    "ワ" to listOf("wa"),
    "ヲ" to listOf("wo"),
    "ン" to listOf("nn", "xn"),
    "ウァ" to listOf("wha"),
    "ウィ" to listOf("wi", "whi"),
    "ウェ" to listOf("we", "whe"),
    "ウォ" to listOf("who"),
    "キャ" to listOf("kya"),
    "キィ" to listOf("kyi"),
    "キュ" to listOf("kyu"),
    "キェ" to listOf("kye"),
    "キョ" to listOf("kyo"),
    "ギャ" to listOf("gya"),
    "ギィ" to listOf("gyi"),
    "ギュ" to listOf("gyu"),
    "ギェ" to listOf("gye"),
    "ギョ" to listOf("gyo"),
    "シャ" to listOf("sya", "sha"),
    "シィ" to listOf("syi"),
    "シュ" to listOf("syu", "shu"),
    "シェ" to listOf("syu", "she"),
    "ショ" to listOf("syo", "sho"),
    "ジャ" to listOf("zya", "ja", "jya"),
    "ジィ" to listOf("zyi", "jyi"),
    "ジュ" to listOf("zyu", "ju", "jyu"),
    "ジェ" to listOf("zye", "je", "jye"),
    "ジョ" to listOf("zyo", "jo", "jyo"),
    "チャ" to listOf("tya", "cya", "cha"),
    "チィ" to listOf("tyi", "cyi"),
    "チュ" to listOf("tyu", "cyu", "chu"),
    "チェ" to listOf("tye", "cye", "che"),
    "チョ" to listOf("tyo", "cyo", "cho"),
    "テャ" to listOf("tha"),
    "ティ" to listOf("thi"),
    "テュ" to listOf("thu"),
    "テェ" to listOf("the"),
    "テョ" to listOf("tho"),
    "デャ" to listOf("dha"),
    "ディ" to listOf("dhi"),
    "デュ" to listOf("dhu"),
    "デェ" to listOf("dhe"),
    "デョ" to listOf("dho"),
    "ニャ" to listOf("nya"),
    "ニィ" to listOf("nyi"),
    "ニュ" to listOf("nyu"),
    "ニェ" to listOf("nye"),
    "ニョ" to listOf("nyo"),
    "ヒャ" to listOf("hya"),
    "ヒィ" to listOf("hyi"),
    "ヒュ" to listOf("hyu"),
    "ヒェ" to listOf("hye"),
    "ヒョ" to listOf("hyo"),
    "ビャ" to listOf("bya"),
    "ビィ" to listOf("byi"),
    "ビュ" to listOf("byu"),
    "ビェ" to listOf("bye"),
    "ビョ" to listOf("byo"),
    "ピャ" to listOf("pya"),
    "ピィ" to listOf("pyi"),
    "ピュ" to listOf("pyu"),
    "ピェ" to listOf("pye"),
    "ピョ" to listOf("pyo"),
    "ファ" to listOf("fa"),
    "フャ" to listOf("fya"),
    "フィ" to listOf("fi"),
    "フュ" to listOf("fyu"),
    "フェ" to listOf("fe"),
    "フォ" to listOf("fo"),
    "フョ" to listOf("fyo"),
    "ヴァ" to listOf("va"),
    "ヴィ" to listOf("vi"),
    "ヴ" to listOf("vu"),
    "ヴェ" to listOf("ve"),
    "ヴォ" to listOf("vo"),
    "ミャ" to listOf("mya"),
    "ミィ" to listOf("myi"),
    "ミュ" to listOf("myu"),
    "ミェ" to listOf("mye"),
    "ミョ" to listOf("myo"),
    "リャ" to listOf("rya"),
    "リィ" to listOf("ryi"),
    "リュ" to listOf("ryu"),
    "リェ" to listOf("rye"),
    "リョ" to listOf("ryo"),
    "ンア" to listOf("nna"),
    "ンイ" to listOf("nni"),
    "ンウ" to listOf("nnu"),
    "ンエ" to listOf("nne"),
    "ンオ" to listOf("nno"),
    "ッカ" to listOf("kka", "cca"),
    "ッガ" to listOf("gga"),
    "ッキ" to listOf("kki"),
    "ッギ" to listOf("ggi"),
    "ック" to listOf("kku", "ccu"),
    "ッグ" to listOf("ggu"),
    "ッケ" to listOf("kke"),
    "ッゲ" to listOf("gge"),
    "ッコ" to listOf("kko", "cco"),
    "ッゴ" to listOf("ggo"),
    "ッサ" to listOf("ssa"),
    "ッザ" to listOf("zza"),
    "ッシ" to listOf("ssi", "cci"),
    "ッジ" to listOf("jji", "zzi"),
    "ッス" to listOf("ssu"),
    "ッズ" to listOf("zzu"),
    "ッセ" to listOf("sse", "cce"),
    "ッゼ" to listOf("zze"),
    "ッソ" to listOf("sso"),
    "ッゾ" to listOf("zzo"),
    "ッジャ" to listOf("jja"),
    "ッジュ" to listOf("jju"),
    "ッジェ" to listOf("jje"),
    "ッジョ" to listOf("jjo"),
    "ッタ" to listOf("tta"),
    "ッダ" to listOf("dda"),
    "ッチ" to listOf("tti"),
    "ッヂ" to listOf("ddi"),
    "ッツ" to listOf("ttu"),
    "ッヅ" to listOf("ddu"),
    "ッテ" to listOf("tte"),
    "ッデ" to listOf("dde"),
    "ット" to listOf("tto"),
    "ッド" to listOf("ddo"),
    "ッハ" to listOf("hha"),
    "ッバ" to listOf("bba"),
    "ッファ" to listOf("ffa"),
    "ッヒ" to listOf("hhi"),
    "ッビ" to listOf("bbi"),
    "ッフィ" to listOf("ffi"),
    "ッフ" to listOf("hhu", "ffu"),
    "ッブ" to listOf("bbu"),
    "ッヘ" to listOf("hhe"),
    "ッベ" to listOf("bbe"),
    "ッフェ" to listOf("ffe"),
    "ッホ" to listOf("hho"),
    "ッボ" to listOf("bbo"),
    "ッフォ" to listOf("ffo"),
    "ッヴァ" to listOf("vva"),
    "ッヴィ" to listOf("vvi"),
    "ッヴ" to listOf("vvu"),
    "ッヴェ" to listOf("vve"),
    "ッヴォ" to listOf("vvo"),
    "ッマ" to listOf("mma"),
    "ッミ" to listOf("mmi"),
    "ッム" to listOf("mmu"),
    "ッメ" to listOf("mme"),
    "ッモ" to listOf("mmo"),
    "ッヤ" to listOf("yya"),
    "ッユ" to listOf("yyu"),
    "ッヨ" to listOf("yyo"),
    "ッラ" to listOf("rra"),
    "ッリ" to listOf("rri"),
    "ッル" to listOf("rru"),
    "ッレ" to listOf("rre"),
    "ッロ" to listOf("rro"),
    "ッワ" to listOf("wwa"),
    "ッウィ" to listOf("wwi"),
    "ッウ" to listOf("wwu"),
    "ッウェ" to listOf("wwe"),
    "ッヲ" to listOf("wwo"),
    "ー" to listOf("-"),
    "・" to listOf("/"),
    "、" to listOf(","),
    "。" to listOf(".")
)