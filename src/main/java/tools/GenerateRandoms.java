package tools;

import org.apache.commons.lang3.RandomStringUtils;

public class GenerateRandoms {

    public String generateString( int length )
    {
        return RandomStringUtils.random(length, true, false);
     }


    public String generateEmail( )
    {
        String name = RandomStringUtils.random(10, true, true);
        String domain = RandomStringUtils.random(10, true, false);
        String zone = RandomStringUtils.random(3, true, false);
        return name + "@" + domain + "." + zone;
    }
}
