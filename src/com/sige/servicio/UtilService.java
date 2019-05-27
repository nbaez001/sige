package com.sige.servicio;

import java.util.List;
import java.util.Map;

public interface UtilService {

	public Map<String, Integer> getLengthColumns(String tableName);

	public void archivarExpedientesVencidos();

	public List<String> getAllTables();
}
