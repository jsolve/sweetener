package pl.jsolve.sweetener.core;

public class FoundGroup {

    private final int startIndex;
    private final int endIndex;
    private final String content;

    public FoundGroup(int startIndex, int endIndex, String content) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.content = content;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "[startIndex=" + startIndex + ", endIndex=" + endIndex + ", content=" + content + "]";
    }

}