package Services;

import org.springframework.stereotype.Service;

@Service
public class GoldToDollarsConverterService {

    public double goldToDollar(double gold){
        return gold * 0.25;
    }

    public double dollarToGold(double dollars){
        return dollars * 4;
    }
}
