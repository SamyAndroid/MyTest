package com.rdissi.mytest.ui.toolkit
import com.rdissi.mytest.data.remote.model.CatalogJson
import com.rdissi.mytest.data.source.JsonConverter.toCatalog
import com.rdissi.mytest.domain.model.Catalog
import com.rdissi.mytest.domain.model.Media
import com.rdissi.mytest.domain.model.Story
import com.rdissi.mytest.domain.model.Video
import com.rdissi.mytest.domain.model.mix
import com.google.gson.Gson
import com.rdissi.mytest.domain.model.sortedByDescending

object MockDataSource {

    fun getMockCatalog(): Catalog {
        val catalogJson: CatalogJson = Gson().fromJson(catalogJsonString, CatalogJson::class.java)
        return catalogJson.toCatalog()
    }

    fun getMockCatalogMixed(): List<Media> {
        val catalog = getMockCatalog()
        return catalog.sortedByDescending().mix()
    }

    fun getFirstMockStory(): Story = getMockCatalog().stories[0]
    fun getFirstMockVideo(): Video = getMockCatalog().videos[0]


    private const val catalogJsonString = "{\n" +
            "  \"videos\": [\n" +
            "    {\n" +
            "      \"id\": 777788,\n" +
            "      \"title\": \"CHRONIQUE FRITSCH\",\n" +
            "      \"thumb\": \"https://i.eurosport.com/2020/01/27/2763745-57096910-2560-1440.jpg\",\n" +
            "      \"url\": \"https://vod-eurosport.akamaized.net/nogeo/2019/10/22/CHRONIQUE_FRITSCH_22102019_V1_22040825-1254400-2300-1024-576.mp4\",\n" +
            "      \"date\": 1588224445.007,\n" +
            "      \"sport\": {\n" +
            "        \"id\": 22,\n" +
            "        \"name\": \"Football\"\n" +
            "      },\n" +
            "      \"views\": 12333\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 777799,\n" +
            "      \"title\": \"Snooker finish\",\n" +
            "      \"thumb\": \"https://i.eurosport.com/2018/09/14/2418542-50269610-2560-1440.jpg\",\n" +
            "      \"url\": \"https://vod-eurosport.akamaized.net/ebu-au/2019/12/08/snookfinish-1269077-700-512-288.mp4\",\n" +
            "      \"date\": 1588104445.007,\n" +
            "      \"sport\": {\n" +
            "        \"id\": 33,\n" +
            "        \"name\": \"Snooker\"\n" +
            "      },\n" +
            "      \"views\": 444\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 777689,\n" +
            "      \"title\": \"Snooker Trohpey\",\n" +
            "      \"thumb\": \"https://i.eurosport.com/2019/12/08/2732922-56480450-2560-1440.jpg\",\n" +
            "      \"url\": \"https://vod-eurosport.akamaized.net/ebu-au/2019/12/08/trophey-1269106-700-512-288.mp4\",\n" +
            "      \"date\": 1488133445.007,\n" +
            "      \"sport\": {\n" +
            "        \"id\": 33,\n" +
            "        \"name\": \"Snooker\"\n" +
            "      },\n" +
            "      \"views\": 1023\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 774689,\n" +
            "      \"title\": \"Il n'en fallait pas plus au Real pour relancer le feuilleton Mbappé\",\n" +
            "      \"thumb\": \"https://imgresizer.eurosport.com/unsafe/1200x0/filters:focal(402x374:404x372)/origin-imgresizer.eurosport.com/2019/09/01/2667509-55172490-2560-1440.jpg\",\n" +
            "      \"url\": \"https://vod-eurosport.akamaized.net/ebu-au/2020/04/30/MERCATO_BUZZ_30042020-5eaaa0331066736b896c4e9f_Apr_30_2020_14_12_23_30041802-1311979-2300-1024-576.mp4\",\n" +
            "      \"date\": 1488113445.007,\n" +
            "      \"sport\": {\n" +
            "        \"id\": 22,\n" +
            "        \"name\": \"Football\"\n" +
            "      },\n" +
            "      \"views\": 8950\n" +
            "    }\n" +
            "  ],\n" +
            "  \"stories\": [\n" +
            "    {\n" +
            "      \"id\": 7738009,\n" +
            "      \"title\": \"Tour de France, arr\\u00EAt de la L1, Agassi-Sampras, 8 secondes : le plateau du jour\",\n" +
            "      \"teaser\": \"Ce soir, c'est le weekend ! En ce 46e jour de confinement, il y a donc toutes les raisons d'avoir la forme. Surtout qu'il va y avoir de l'actualit\\u00E9 ce jeudi. Cerise sur le g\\u00E2teau : si vous voulez vous replonger dans les plus belles heures du sport, on a tout ce qu\\u2019il faut pour vous. Voici notre plateau matinal.\",\n" +
            "      \"image\": \"https://i.eurosport.com/2020/04/30/2812653-58018086-640-220.jpg\",\n" +
            "      \"date\": 1588222335.007,\n" +
            "      \"author\": \"John doe\",\n" +
            "      \"sport\": {\n" +
            "        \"id\": 95,\n" +
            "        \"name\": \"Omnisport\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 7738025,\n" +
            "      \"title\": \"Mourad de Toulon : \\\"Tillous-Borde m\\u00E9rite le respect. J'esp\\u00E8re que le RCT s'en souviendra\\\"\",\n" +
            "      \"teaser\": \"Toujours confin\\u00E9 \\u00E0 son domicile, Mourad Boudjellal est un spectateur attentif du monde du rugby. Gr\\u00E2ce \\u00E0 son \\u00E9pouse Linda et sa fille Roselaine, transform\\u00E9es en r\\u00E9alisatrices, l'ancien pr\\u00E9sident du RCT porte son regard sur l'ouverture de la saison des transferts ou sur le salaire de Pollard \\u00E0 Montpellier. Le coup de coeur de la semaine, il est quant \\u00E0 lui pour S\\u00E9bastien Tilllous-Borde...\",\n" +
            "      \"image\": \"https://i.eurosport.com/2020/04/29/2812626-58017546-640-220.png\",\n" +
            "      \"date\": 1588224705.843,\n" +
            "      \"author\": \"Mike Swiss\",\n" +
            "      \"sport\": {\n" +
            "        \"id\": 44,\n" +
            "        \"name\": \"Rugby\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 7737839,\n" +
            "      \"title\": \"Malgr\\u00E9 l'arr\\u00EAt de la L1, le PSG et Lyon pourraient jouer en C1 selon un membre ex\\u00E9cutif de l'UEFA\",\n" +
            "      \"teaser\": \"Andre Agassi f\\u00EAte mercredi son 50e anniversaire. Pour l'occasion, on vous propose un quiz sur un aspect important de l'oeuvre du \\\"Kid de Las Vegas\\\" : le style. A vous d'identifier s'il s'agit ou non de l'homme aux huit titres du Grand Chelem sur les photos suivantes.\",\n" +
            "      \"image\": \"https://i.eurosport.com/2009/10/28/554648-6892499-128-96.jpg\",\n" +
            "      \"date\": 1588112029.433,\n" +
            "      \"author\": \"R\\u00E9my De Souza\",\n" +
            "      \"sport\": {\n" +
            "        \"id\": 22,\n" +
            "        \"name\": \"Football\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 7736814,\n" +
            "      \"title\": \"Quiz - Agassi a 50 ans mais a-t-il port\\u00E9 ces tenues ?\",\n" +
            "      \"teaser\": \"Andre Agassi a 50 ans ce jour. Entre 1986 et 2006, il en a pass\\u00E9 20 sur le circuit, ces m\\u00EAmes 20 ann\\u00E9es durant lesquelles j'ai fa\\u00E7onn\\u00E9 ma passion pour le tennis au rythme de sa grandeur ou, parfois, de sa d\\u00E9cadence. Il est LE champion de ma jeunesse, celui qui m'aura le plus marqu\\u00E9, pour tout un tas de raisons. Voici donc mon ode \\u00E0 D\\u00E9d\\u00E9.\",\n" +
            "      \"image\": \"https://i.eurosport.com/2019/11/27/2725182.jpg\",\n" +
            "      \"date\": 1588110350.85,\n" +
            "      \"author\": \"R\\u00E9mi Bourri\\u00E8res\",\n" +
            "      \"sport\": {\n" +
            "        \"id\": 57,\n" +
            "        \"name\": \"Tennis\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 7737687,\n" +
            "      \"title\": \"Une pinc\\u00E9e de mauvaise foi mais pas que : Les playoffs d\\u2019Aulas sont-ils pertinents ?\",\n" +
            "      \"teaser\": \"LIGUE 1 - Alors qu\\u2019Edouard Philippe a confirm\\u00E9 mardi l\\u2019arr\\u00EAt des championnats, Jean-Michel Aulas a propos\\u00E9 de jouer des playoffs en ao\\u00FBt pour terminer la saison. Sous quelle forme les organiser ? Pourraient-ils vraiment se jouer ? Est-ce que des playoffs arrangeraient l\\u2019Olympique lyonnais ? Zoom sur la proposition pol\\u00E9mique du pr\\u00E9sident des Gones..\",\n" +
            "      \"image\": \"https://i.eurosport.com/_iss_/sport/football/competition/logo/large/54.png\",\n" +
            "      \"date\": 1588171753.103,\n" +
            "      \"author\": \"Sasha Beckermann\",\n" +
            "      \"sport\": {\n" +
            "        \"id\": 22,\n" +
            "        \"name\": \"Football\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 7737812,\n" +
            "      \"title\": \"\\uD83C\\uDFA7 Huit secondes\",\n" +
            "      \"teaser\": \"PODCAST - LES GRANDS RECITS - Le 23 juillet 1989 s'achevait un des Tours de France les plus extraordinaires de l'histoire. Trois semaines \\u00E9piques, entre rebondissements, suspense et tensions. Un Tour qui s'est r\\u00E9sum\\u00E9 \\u00E0 deux hommes, Greg LeMond et Laurent Fignon. Et \\u00E0 un chiffre : huit. Comme les huit secondes qui vont s\\u00E9parer les deux hommes \\u00E0 Paris et les r\\u00E9unir \\u00E0 jamais dans la l\\u00E9gende.\",\n" +
            "      \"image\": \"https://i.eurosport.com/2013/07/02/1037463-16689785-128-96.jpg\",\n" +
            "      \"date\": 1588197999.47,\n" +
            "      \"author\": \"Hadrien Hiault\",\n" +
            "      \"sport\": {\n" +
            "        \"id\": 18,\n" +
            "        \"name\": \"Cyclisme\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 7736790,\n" +
            "      \"title\": \"Nombre de courses, huis clos, format de week-ends... : 10 enjeux pour sauver la F1\",\n" +
            "      \"teaser\": \"FORMULE 1 2020 - Des courses \\u00E0 huis clos \\u00E9tant la seule solution envisageable pour que des Grands Prix aient lieu cette saison, des questions se posent sur leur nombre pour faire un championnat du monde, leurs conditions sanitaires ou encore leurs montages financiers. \\u00C9l\\u00E9ments de r\\u00E9ponses.\",\n" +
            "      \"image\": \"https://i.eurosport.com/2013/07/02/1037463-16689785-128-96.jpg\",\n" +
            "      \"date\": 1588197274.257,\n" +
            "      \"author\": \"St\\u00E9phane Vrignaud\",\n" +
            "      \"sport\": {\n" +
            "        \"id\": 25,\n" +
            "        \"name\": \"Formule 1\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 7735290,\n" +
            "      \"title\": \"Du moins marquant au plus mythique, nous avons class\\u00E9 les 34 duels de la rivalit\\u00E9 Sampras-Agassi\",\n" +
            "      \"teaser\": \"Comme tous les grands de l\\u2019Histoire du jeu, Andre Agassi a d\\u00FB ferrailler avec un rival de choix pour forger sa propre l\\u00E9gende. Qui sait si, sans Pete Sampras, le \\\"Kid de Las Vegas\\\" ne serait pas plus souvent cit\\u00E9 dans le d\\u00E9bat r\\u00E9current sur l\\u2019hypoth\\u00E9tique meilleur joueur de tous les temps ? Retour sur les 34 duels (20-14 pour Sampras) entre les deux Am\\u00E9ricains aux styles si compl\\u00E9mentaires.\",\n" +
            "      \"image\": \"https://i.eurosport.com/2020/04/29/2812635-58017726-640-220.jpg\",\n" +
            "      \"date\": 1588196794.537,\n" +
            "      \"author\": \"Maxime Battistella\",\n" +
            "      \"sport\": {\n" +
            "        \"id\": 57,\n" +
            "        \"name\": \"Tennis\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 7736568,\n" +
            "      \"title\": \"Mes ann\\u00E9es \\\"D\\u00E9d\\u00E9\\\" : on a tous en nous quelque chose d'Agassi\",\n" +
            "      \"teaser\": \"Andre Agassi a 50 ans ce jour. Entre 1986 et 2006, il en a pass\\u00E9 20 sur le circuit, ces m\\u00EAmes 20 ann\\u00E9es durant lesquelles j'ai fa\\u00E7onn\\u00E9 ma passion pour le tennis au rythme de sa grandeur ou, parfois, de sa d\\u00E9cadence. Il est LE champion de ma jeunesse, celui qui m'aura le plus marqu\\u00E9, pour tout un tas de raisons. Voici donc mon ode \\u00E0 D\\u00E9d\\u00E9.\",\n" +
            "      \"image\": \"https://i.eurosport.com/2019/11/27/2725182.jpg\",\n" +
            "      \"date\": 1588110350.85,\n" +
            "      \"author\": \"R\\u00E9mi Bourri\\u00E8res\",\n" +
            "      \"sport\": {\n" +
            "        \"id\": 57,\n" +
            "        \"name\": \"Tennis\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 7737742,\n" +
            "      \"title\": \"La Vuelta amput\\u00E9e de trois \\u00E9tapes\",\n" +
            "      \"teaser\": \"TOUR D'ESPAGNE - Contrainte de changer de place dans le calendrier suite au report du Tour de France et dans un contexte de crise du coronavirus, la Vuelta 2020 ne comptera que 18 \\u00E9tapes au lieu des 21 pr\\u00E9vues. Les trois journ\\u00E9es programm\\u00E9es aux Pays-Bas sont ainsi supprim\\u00E9es et le d\\u00E9part est pr\\u00E9vu au Pays Basque entre Irun et Arate.\",\n" +
            "      \"image\": \"https://i.eurosport.com/2019/11/27/2725182.jpg\",\n" +
            "      \"date\": 1588170827.457,\n" +
            "      \"author\": \"Simon Farvacque\",\n" +
            "      \"sport\": {\n" +
            "        \"id\": 18,\n" +
            "        \"name\": \"Cyclisme\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 7736588,\n" +
            "      \"title\": \"Guillen : \\\"La Vuelta doit \\u00EAtre la preuve que nous retrouvons nos vies\\\"\",\n" +
            "      \"teaser\": \"TOUR D'ESPAGNE - La Vuelta f\\u00EAte son 85e anniversaire cette semaine. Mais on ignore encore quand et comment elle se disputera en 2020. Une chose est s\\u00FBre, elle est esp\\u00E9r\\u00E9e et attendue par Javier Guillen, directeur du Grand Tour espagnol.\",\n" +
            "      \"image\": \"https://i.eurosport.com/2017/04/14/2062976-43255022-128-96.jpg\",\n" +
            "      \"date\": 1588165865.817,\n" +
            "      \"author\": \"Beno\\u00EEt Vittek\",\n" +
            "      \"sport\": {\n" +
            "        \"id\": 18,\n" +
            "        \"name\": \"Cyclisme\"\n" +
            "      }\n" +
            "    }\n" +
            "  ]\n" +
            "}"
}