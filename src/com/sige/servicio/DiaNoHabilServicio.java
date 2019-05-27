package com.sige.servicio;

import java.util.List;

import com.sige.entidad.DiaNoHabil;
import com.sige.util.Busqueda;

public interface DiaNoHabilServicio extends BaseServicio<DiaNoHabil, Long> {

	public List<DiaNoHabil> buscarPorDiaNoHabil(DiaNoHabil diaNoHabil);

	public Busqueda buscarPorDiaNoHabil(DiaNoHabil diaNoHabil, Long paginaActual);

	public Busqueda buscarPorDiaNoHabil(DiaNoHabil diaNoHabil,
			Long paginaActual, Object fechaInicio, Object fechaFinal);

	public void eliminarDiaNoHabil(Long id);

	public String getDiaNoHabil(Long id);

	public Long getCantidadRegistros();

	public Boolean validarDuplicado(DiaNoHabil diaNoHabil);

	public Long getCantidadDiasNoHabiles(Object fechaInicio, Object fechaFinal);

	public Integer getDiasPlazoVencimiento();

}
