package GUI.Utility;

import javafx.scene.web.WebView;

public class QuillHTMLEditor {

    private WebView webView;

    public QuillHTMLEditor(String text) {
        this.webView = new WebView();

        webView.setMaxHeight(375);

        webView.getEngine().loadContent("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <!-- Include stylesheet -->\n" +
                "    <link href=\"https://cdn.quilljs.com/1.3.6/quill.snow.css\" rel=\"stylesheet\">\n" +
                "\n" +
                "    <!-- Include the Quill library -->\n" +
                "    <script src=\"https://cdn.quilljs.com/1.3.6/quill.js\"></script>\n" +
                "\n" +
                "    <style>\n" +
                "        #editor {\n" +
                "            height: 375;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body style=\"background-color: rgba(0,0,0,0.14);\">\n" +
                "<!-- Create the editor container -->\n" +
                "<div id=\"editor\" style=\"background-color: #fff;\">\n" +
                    text +
                "</div>\n" +
                "\n" +
                "<!-- Initialize Quill editor -->\n" +
                "<script>\n" +
                "var toolbarOptions = [\n" +
                "  [{ 'header': [1, 2, 3, 4, 5, 6, false] }],\n" +
                "  [{ 'align': [] }],\n" +
                "  ['bold', 'italic', 'underline', 'strike'],        // toggled buttons\n" +
                "  ['blockquote', 'code-block'],\n" +
                "\n" +
                "  [{ 'list': 'ordered'}, { 'list': 'bullet' }],\n" +
                "  [{ 'script': 'sub'}, { 'script': 'super' }],      // superscript/subscript\n" +
                "  [{ 'indent': '-1'}, { 'indent': '+1' }],          // outdent/indent\n" +
                "\n" +
                "  [{ 'size': ['small', false, 'large', 'huge'] }],  // custom dropdown\n" +
                "\n" +
                "  [{ 'color': [] }, { 'background': [] }],          // dropdown with defaults from theme\n" +
                "\n" +
                "  ['clean'],                                        // remove formatting button\n" +
                "  ['link', 'image', 'video']                        // link and image, video\n" +
                "];\n" +
                "\n" +
                "var quill = new Quill('#editor', {\n" +
                "  modules: {\n" +
                "    toolbar: toolbarOptions\n" +
                "  },\n" +
                "  theme: 'snow'\n" +
                "});" +
                "</script>\n" +
                "<style>\n" +
                        ".ql-toolbar {\n" +
                        "    background-color: white;\n" +
                        "}\n" +
                "</style>" +
                "</body>\n" +
                "</html>");
    }

    public WebView getWebView() {
        return webView;
    }


    public String getText() {
        // Use JavaScript to get the text of the Quill editor
        return (String) webView.getEngine().executeScript("quill.getText();");
    }
}