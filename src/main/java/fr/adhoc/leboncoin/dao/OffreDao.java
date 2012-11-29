package fr.adhoc.leboncoin.dao;

import java.util.List;

import fr.adhoc.leboncoin.model.Offre;

public interface OffreDao {
	boolean create(Offre offre);

	List<Offre> findAll();
}