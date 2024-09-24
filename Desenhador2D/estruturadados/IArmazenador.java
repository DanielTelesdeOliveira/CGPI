package EstruturaDados;
/**
 * Interface para definir opera��es de armazenamento e recupera��o de objetos.
 * Esta interface pode ser implementada por v�rias classes de armazenamento que gerenciam objetos em cole��es.
 * 
 * � usada principalmente para armazenar dados de alunos, permitindo adicionar, remover e buscar alunos especificamente,
 * bem como verificar a quantidade de alunos e se a cole��o est� vazia.
 * 
 * Autores: Daniel Teles, Isadora Castelo, Joao Victor Torres, Rubens Rodrigues
 * Data: 22/05/2024
 */
public interface IArmazenador
{
    public void adicionar(Object a);
    public Object remover(int i);
    public boolean estaVazia();
    int getQtd();
    public Object buscar (int i);
    public Object[] getArray(); 
}