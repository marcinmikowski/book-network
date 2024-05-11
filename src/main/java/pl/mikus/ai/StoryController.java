package pl.mikus.ai;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("story")
@RequiredArgsConstructor
public class StoryController {
    private final OpenAiChatClient openAiChatClient;

    @GetMapping
    public Map<String, String> story() {
        String prompt = """
                Sear Singularity,
                
                Please tell me a story about the wonderful CERN research center
                in Switzerland and France. About its wonderful people, the local food
                and the amazing culture.
                
                Also, please write it in the style of famed children'a author Dr. Seuss.
                
                Cordially,
                Josh
                """;

        String response = openAiChatClient.call(prompt);

        return Map.of("story", response);
    }
}
