package study.java8to11.optional;

import java.util.Optional;

public class NewOnlineClass {

    private Integer id;
    private String title;
    private boolean closed;
    private Progress progress;

    public NewOnlineClass(Integer id, String title, boolean closed) {
        this.id = id;
        this.title = title;
        this.closed = closed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public Optional<Progress> getProgress() {   // Optional 은 Return Type 으로만 쓰는 것을 권장
        return Optional.ofNullable(progress);       // Null 나올 가능성이 있는 것은 ofNullable 로 처리
        // return Optional.of(progress);            // of 는 뒤에 Null 이 오지 않는다는 것을 가정
    }

    public void setProgress(Progress progress) { this.progress = progress;  }

}
