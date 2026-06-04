package dk.sdu.mmmi.cbse.scoreservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScoreController {
    private int score = 0;

    @GetMapping("/score")
    public int getScore(){
        return score;
    }

    @PostMapping("/score/add")
    public int setScore(@RequestParam int addPoint){
        score += addPoint;
        return score;
    }
}

