package edu.purdue.app.news.data;

import edu.purdue.app.R;

/**
 *  Java enums are a great solution to this problem that we have three sets of data where
 *  a given index across each set represents a tuple of related data.
 *
 *  Created by mike on 2/2/15.
 */
public enum DefaultNewsCategories {

    academic
            ("Academic", R.drawable.news_ic_academic, "http://www.purdue.edu/newsroom/rss/academics.xml"),
    agriculture
            ("Agriculture", R.drawable.news_ic_agriculture, "http://www.purdue.edu/newsroom/rss/AgriNews.xml"),
    business
            ("Business", R.drawable.news_ic_business, "http://www.purdue.edu/newsroom/rss/BizNews.xml"),
    community
            ("Community", R.drawable.news_ic_community, "http://www.purdue.edu/newsroom/rss/community.xml"),
    diversity
            ("Diversity", R.drawable.news_ic_diversity, "http://www.purdue.edu/newsroom/rss/DiversityNews.xml"),
    edu_career
            ("Education and Career", R.drawable.news_ic_edu_career, "http://www.purdue.edu/newsroom/rss/EdCareerNews.xml"),
    events
            ("Events", R.drawable.news_ic_events, "http://www.purdue.edu/newsroom/rss/EventNews.xml"),
    featured
            ("Featured", R.drawable.news_ic_featured, "http://www.purdue.edu/newsroom/rss/FeaturedNews.xml"),
    general
            ("General", R.drawable.news_ic_general, "http://www.purdue.edu/newsroom/rss/general.xml"),
    health
            ("Health and Medicine", R.drawable.news_ic_health_medicine, "http://www.purdue.edu/newsroom/rss/HealthMedNews.xml"),
    it
            ("Information Technology", R.drawable.news_ic_it, "http://www.purdue.edu/newsroom/rss/InfoTech.xml"),
    lifestyle
            ("Lifestyle", R.drawable.news_ic_lifestyle, "http://www.purdue.edu/newsroom/rss/LifeNews.xml"),
    lifesci
            ("Life Sciences", R.drawable.news_ic_life_sciences, "http://www.purdue.edu/newsroom/rss/LifeSciNews.xml"),
    outreach
            ("Outreach", R.drawable.news_ic_outreach, "http://www.purdue.edu/newsroom/rss/outreach.xml"),
    physicalsci
            ("Physical Sciences", R.drawable.news_ic_physical_sciences, "http://www.purdue.edu/newsroom/rss/PhysicalSciNews.xml"),
    research
            ("Research Foundation", R.drawable.news_ic_research, "http://www.purdue.edu/newsroom/rss/ResearchNews.xml"),
    student
            ("Student", R.drawable.news_ic_student, "http://www.purdue.edu/newsroom/rss/StudentNews.xml"),
    vetmed
            ("Veterinary Medicine", R.drawable.news_ic_vet_med, "http://www.purdue.edu/newsroom/rss/VetMedNews.xml");

    private String printable;
    private int drawableResource;
    private String url;

    DefaultNewsCategories(String printable, int drawableResource, String url) {
        this.printable = printable;
        this.drawableResource = drawableResource;
        this.url = url;
    }

    public String printable() {
        return printable;
    }

    public int drawableResource() {
        return drawableResource;
    }

    public String url() {
        return url;
    }

}
