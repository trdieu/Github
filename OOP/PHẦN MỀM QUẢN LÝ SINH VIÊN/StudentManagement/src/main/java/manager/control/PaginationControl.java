/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager.control;
/**
 *
 * @author Dell
 */
public class PaginationControl {

    public int getSize() {
        return size;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getNumPerPage() {
        return numPerPage;
    }

    public void setSize(int size) {
        this.size = size;
        nPage = (int)Math.ceil((double)size/numPerPage);
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setNumPerPage(int numPerPage) {
        this.numPerPage = numPerPage;
    }

    public int getnPage() {
        return nPage;
    }
    
    public boolean hasNext() {
        return currentPage < nPage;
    }

    public boolean hasPrevious() {
        return currentPage > 1;
    }
    
    public int next() {
        if(hasNext()) {
            currentPage++;
        }
        return currentPage;
    }
    
    public int previous() {
        if(hasPrevious()) {
            currentPage--;
        }
        return currentPage;
    }
    
    public int getStartOfPage() {
        return numPerPage*(currentPage - 1) + 1;
    }
    
    public int getEndOfPage() {
        int end = getStartOfPage() + 20;
        if(end > size) {
            end = size;
        }
        return end;
    }
    
    private int nPage;
    private int size;
    private int currentPage = 1;
    private int numPerPage = 20; 
}
