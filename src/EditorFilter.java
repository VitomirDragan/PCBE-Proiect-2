public class EditorFilter extends Filter{

    private Editor editor;

    public EditorFilter(Editor editor)
    {
        this.editor = editor;
    }

    @Override
    public boolean checkFilter(News news) {
        boolean result = false;

        if(news.getAutor().equals(this.editor.getName()))
            result =true;

        return false;
    }
}
