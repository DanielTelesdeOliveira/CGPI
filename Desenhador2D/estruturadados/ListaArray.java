package EstruturaDados; 
import java.util.ArrayList;
import java.io.Serializable;

/**
 * Implementa a interface IArmazenador usando um ArrayList para armazenar objetos genericamente.
 * Esta classe oferece m�todos para adicionar, remover, buscar e verificar se a lista est� vazia,
 * al�m de m�todos para obter a quantidade de objetos armazenados.
 * 
 * Autores: Daniel Teles, Isadora Castelo, Joao Victor Torres, Rubens Rodrigues
 * Data: 22/05/2024
 */
public class ListaArray implements IArmazenador, Serializable {

    private ArrayList<Object> lista; // Armazenamento dos objetos em um ArrayList

    /**
     * Construtor padr�o que inicializa a lista.
     */
    public ListaArray() {
        lista = new ArrayList<>();
    }

    /**
     * Retorna a lista de objetos.
     *
     * @return ArrayList de objetos.
     */
    public ArrayList<Object> getLista() {
        return lista;
    }

    /**
     * Retorna a quantidade de objetos na lista.
     *
     * @return N�mero de objetos na lista.
     */
    public int getQtd() {
        return lista.size();
    }

    /**
     * Define a lista de objetos.
     *
     * @param lista A nova lista de objetos a ser configurada.
     */
    private void setLista(ArrayList<Object> lista) {
        this.lista = lista;
    }

    /**
     * Adiciona um objeto � lista.
     *
     * @param obj O objeto a ser adicionado.
     */
    public void adicionar(Object obj) {
        lista.add(obj);
    }

    /**
     * Remove um objeto da lista pelo �ndice.
     *
     * @param i O �ndice do objeto a ser removido.
     * @return O objeto removido, null se nenhum objeto foi removido.
     */
    public Object remover(int i) {
        Object ret = null;
        if (buscar(i) != null) {
            ret = lista.get(i);
            lista.remove(i);
        }
        return ret;
    }

    /**
     * Busca um objeto na lista pelo �ndice.
     *
     * @param i O �ndice do objeto.
     * @return O objeto no �ndice dado, null se o �ndice for inv�lido ou a lista estiver vazia.
     */
    public Object buscar(int i) {
        if (!lista.isEmpty() && (i >= 0 && i < getQtd())) {
            return lista.get(i);
        }
        return null;
    }

    /**
     * Verifica se a lista est� vazia.
     *
     * @return true se a lista estiver vazia, false caso contr�rio.
     */
    public boolean estaVazia() {
        return lista.isEmpty();
    }

    /**
     * Retorna uma representa��o em string da lista, contendo todos os objetos.
     *
     * @return Uma string representando todos os objetos na lista.
     */
    public String toString() {
        StringBuilder s = new StringBuilder("[ ");
        for (Object obj : lista) {
            s.append(obj.toString()).append(" ");
        }
        s.append("]");
        return s.toString();
    }

    /**
     * Converte a lista para um array de objetos.
     *
     * @return Um array contendo todos os objetos armazenados na lista.
     */
    public Object[] getArray() {
        Object[] obj = new Object[getQtd()];
        for (int i = 0; i < obj.length; i++) {
            obj[i] = lista.get(i);
        }
        return obj;
    }
}
