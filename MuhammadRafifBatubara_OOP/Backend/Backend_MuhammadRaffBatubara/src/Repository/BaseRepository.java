package Repository;

import java.util.*;

public abstract class BaseRepository<T, ID> {
    // Attribute Map untuk pencarian cepat berdasarkan ID [cite: 12]
    Map<ID, T> dataMap = new HashMap<>();

    // Attribute List untuk menyimpan semua data, bersifat protected [cite: 13]
    protected List<T> allData = new ArrayList<>();

    /**
     * Mengembalikan data berdasarkan ID, dibungkus dalam Optional[cite: 8, 15].
     */
    public Optional<T> findById(ID id) {
        return Optional.ofNullable(dataMap.get(id));
    }

    /**
     * Mengembalikan salinan dari seluruh data yang tersimpan[cite: 16].
     */
    public List<T> findAll() {
        return new ArrayList<>(allData);
    }

    // Abstract method untuk menyimpan entitas [cite: 18]
    public abstract void save(T entity);

    // Abstract method untuk mendapatkan ID dari entitas [cite: 20]
    public abstract ID getId(T entity);
}