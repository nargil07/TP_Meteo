package fr.iut_valence.tp_meteo.SQLite.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import fr.iut_valence.tp_meteo.SQLite.MeteoOpenHelper;
import fr.iut_valence.tp_meteo.SQLite.SQLiteConstante;
import fr.iut_valence.tp_meteo.exception.BadJsonExpectException;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

/**
 * Cette classe abstraite permet de gérer les entrées sorties d'une base de données sqlite sur android<br />
 * Created 04/12/2015.
 *
 * @author antony
 * @version 1.0.2
 * @see SQLiteConstante
 */
public abstract class AbstractDAO<T> implements SQLiteConstante {
    /**
     * L"acesseur de la base de données ouverte avec La base MeteoOpenHelper
     */
    protected SQLiteDatabase bdd;
    /**
     * La base de donnees MeteoOpenHelper
     */
    private MeteoOpenHelper maBase;

    /**
     * Crée un nouvel objet de type AbstractDAO
     *
     * @param context context nécéssaire pour la création de la base de données
     */
    public AbstractDAO(Context context) {
        maBase = new MeteoOpenHelper(context, DB_NAME, null, DB_VERSION);
    }

    /**
     * Ouvre en écriture la base
     */
    protected void open() {
        bdd = maBase.getWritableDatabase();
    }

    /**
     * ferme l'accès a la base.
     */
    protected void close() {
        bdd.close();
    }

    /**
     * Cette méthode dois pouvoir renvoyer une {@code entité} du type de la classe graçe à son identifiant<br />
     *
     * @param id l'identifiant qui est une clé primaire dans la base.
     * @return {@code entity} l'objet unique de la base.
     */
    public abstract T get(String id);

    /**
     * Cette méthode doit pouvoir modifier une {@code entité} du type de la classe.
     * @param entity {@code entité} qui sera modifié dans la base.
     */
    public abstract void update(T entity);

    /**
     * Cette méthode doit permetre d'insérer un ou plusieurs {@code entité} du type de la classe avec le JSONOBJECT.<br />
     * Le json doit comprendre une liste {@code d'entité} du type de la classe qui seront inserer dans la base.<br />
     * Si le json ne correspond pas au type d'objet attendu, il renvoie une BadJsonExpectedException
     *
     * @param jsonArray La liste d'objet json a inserer
     * @throws fr.iut_valence.tp_meteo.exception.BadJsonExpectException Si le json ne correspond pas a l'objet a inserer
     */
    public abstract void insertByJson(JSONArray jsonArray) throws BadJsonExpectException;

    /**
     * Cette méthode doit permetre d'inserer une {@code entité} du type de la classe dans la base sqlite. <br />
     * Si il manque des informations, l'objet n'est pas inséré.
     *
     * @param entity L'objet a inserer en base.
     */
    public abstract void add(T entity);


    /**
     * Cette méthode doit permettre de récuperer toutes les {@code entités} du type de la classe
     *
     * @return la liste {@code d'entites}
     */
    public abstract List<T> getAll();

}
