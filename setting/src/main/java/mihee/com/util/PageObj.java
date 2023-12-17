package mihee.com.util;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class PageObj<T> implements Iterable<T>, Serializable {

    private List<T> content = new ArrayList<>();
    private long totalElements;
    private int pageNumber;
    private int pageSize;
    private boolean first;
    private boolean last;
    private boolean empty;
    private int totalPages;
    private int numberOfElements;
    private boolean previous;
    private boolean next;
    public PageObj(Page<T> page) {
        this.content = page.getContent();
        this.totalElements = page.getTotalElements();
        this.pageNumber = page.getPageable().getPageNumber();
        this.pageSize = page.getPageable().getPageSize();
        this.numberOfElements = page.getNumberOfElements();
        this.previous = page.getPageable().getPageNumber() > 0;
        this.next = page.getPageable().getPageNumber() + 1 < this.totalPages;
    }

    public boolean hasPrevious() {
        return getPageNumber() > 0;
    }

    public boolean hasNext() {
        return getPageNumber() + 1 < getTotalPages();
    }

    public boolean isFirst() {
        return !hasPrevious();
    }
    public boolean isLast() {
        return !hasNext();
    }

    public List<T> getContent() {
        return Collections.unmodifiableList(content);
    }

    public void setContent(List<T> content) {
        this.content = content;
    }
    public boolean hasContent() {
        return getNumberOfElements() > 0;
    }
    public int getTotalPages() {
        return getPageSize() == 0 ? 1 : (int) Math.ceil((double) totalElements /(double) getPageSize());
    }

    public boolean isEmpty () {
        return !hasContent();
    }
    @Override
    public Iterator<T> iterator() {
        return getContent().iterator();
    }

}
