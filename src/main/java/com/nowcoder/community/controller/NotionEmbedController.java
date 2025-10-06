package com.nowcoder.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.MediaType;

import java.time.LocalDateTime;

@Controller
public class NotionEmbedController {

    @GetMapping(value = "/embed/hello", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String helloEmbed() {
        return """
            <!DOCTYPE html>
            <html>
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <style>
                    body {
                        font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', sans-serif;
                        margin: 0;
                        padding: 20px;
                        background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
                        color: white;
                        min-height: 100vh;
                        display: flex;
                        align-items: center;
                        justify-content: center;
                    }
                    .container {
                        text-align: center;
                    }
                    h1 {
                        font-size: 2.5em;
                        margin-bottom: 20px;
                    }
                    p {
                        font-size: 1.2em;
                    }
                </style>
            </head>
            <body>
                <div class="container">
                    <h1>🎨 Hello from Spring Boot!</h1>
                    <p>This page is embedded in Notion</p>
                    </p>
                </div>
            </body>
            </html>
            """;
    }

    @GetMapping(value = "/embed/simple", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String simpleEmbed() {
        return "<h1>Simple Test</h1><p>If you see this, the embed is working!</p>";
    }

    @GetMapping(value = "/embed/debug", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String debugEmbed() {
        String s = """
                <html>
                <body style="font-family: monospace; padding: 20px;">
                    <h2>Debug Info</h2>
                    <p>This is a minimal test page for embedding.</p>
                    <script>
                        console.log('Embed loaded successfully');
                        document.body.style.border = '2px solid green';
                    </script>
                </body>
                </html>
                """;
        return s;
    }

}
