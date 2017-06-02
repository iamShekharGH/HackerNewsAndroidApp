package com.iamshekhargh.hackernews.environment;

/**
 * Created by <<-- iamShekharGH -->>
 * on 14 May 2017
 * at 12:28 PM.
 */

public class EnvironmentProvider {

    private enum WhichEnvironment {
        HN, ALGOLIA
    }

    WhichEnvironment whichEnvironment = WhichEnvironment.HN;

    public Environment getEnvironment() {
        switch (whichEnvironment) {
            case HN:
                return new HNEnvironmane();

            case ALGOLIA:
                return new AlgoliaEnvironment();

            default:
                return new HNEnvironmane();
        }
    }
}
