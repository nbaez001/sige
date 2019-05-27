package com.sige.servicio.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sige.entidad.DiaNoHabil;
import com.sige.repositorio.DiaNoHabilRepositorio;
import com.sige.servicio.DiaNoHabilServicio;
import com.sige.util.Busqueda;
import com.sige.util.Constantes;
import com.sige.util.Criterio;

@Service
public class DiaNoHabilServicioImpl extends BaseServicioImpl<DiaNoHabil, Long>
		implements DiaNoHabilServicio {

	@Autowired
	private DiaNoHabilRepositorio diaNoHabilRepositorio;

	//@Value("${vencimientoExpediente.diasPlazo}")
	private Integer diasPlazoVencimiento=SistemaProperties.vencimientoExpedienteDiasPlazo;

	@Autowired
	public DiaNoHabilServicioImpl(DiaNoHabilRepositorio diaNoHabilRepositorio) {
		super(diaNoHabilRepositorio);
		this.diaNoHabilRepositorio = diaNoHabilRepositorio;
	}

	@Override
	public Busqueda buscarPorDiaNoHabil(DiaNoHabil diaNoHabil,
			Long paginaActual, Object fechaInicio, Object fechaFinal) {
		Busqueda busqueda = new Busqueda();
		Criterio filtro = Criterio.forClass(DiaNoHabil.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE));
		// filtro.add(Restrictions.ilike("año",diaNoHabil.getAño(),MatchMode.ANYWHERE));
		Date dateInicio = (Date) fechaInicio;
		Date dateFinal = (Date) fechaFinal;
		filtro.add(Restrictions.between("fecha", dateInicio, dateFinal));
		// filtro.add(Restrictions.ilike("abreviatura",tipoLugar.getAbreviatura(),MatchMode.ANYWHERE));

		Long totalRegistros = diaNoHabilRepositorio.cantidadPorCriteria(filtro);
		if (totalRegistros % Constantes.PAGINACION.USUARIO == 0) {
			busqueda.setNumeroPaginas(totalRegistros
					/ Constantes.PAGINACION.USUARIO);
		} else {
			busqueda.setNumeroPaginas(totalRegistros
					/ Constantes.PAGINACION.USUARIO + 1);
		}
		// validaciones
		if (paginaActual > busqueda.getNumeroPaginas()) {
			busqueda.setPaginaActual(busqueda.getNumeroPaginas());
		} else {
			busqueda.setPaginaActual(paginaActual);
		}
		if (paginaActual <= 0) {
			busqueda.setPaginaActual(1L);
		}
		// /
		// obtenemos los registros de la pagina solicitada
		filtro.setMaxResults(Constantes.PAGINACION.USUARIO.intValue());
		filtro.setFirstResult((busqueda.getPaginaActual().intValue() - 1)
				* Constantes.PAGINACION.USUARIO.intValue());
		filtro.addOrder(Order.asc("año"));
		if(totalRegistros>0){
		busqueda.setRegistos(diaNoHabilRepositorio.buscarPorCriteria(filtro));
		}else{
			busqueda.setRegistos(new ArrayList<>());
		}

		return busqueda;
	}

	@Override
	public Long getCantidadDiasNoHabiles(Object fechaInicio, Object fechaFinal) {
		Criterio filtro = Criterio.forClass(DiaNoHabil.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE));
		Date dateInicio = (Date) fechaInicio;
		Date dateFinal = (Date) fechaFinal;
		filtro.add(Restrictions.between("fecha", dateInicio, dateFinal));

		return diaNoHabilRepositorio.cantidadPorCriteria(filtro);

	}

	@Override
	public List<DiaNoHabil> buscarPorDiaNoHabil(DiaNoHabil diaNoHabil) {
		Criterio filtro = Criterio.forClass(DiaNoHabil.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE));
		filtro.add(Restrictions.ilike("año", diaNoHabil.getAño(),MatchMode.ANYWHERE));
		// filtro.add(Restrictions.eq("fecha",diaNoHabil.getFecha()));
		filtro.addOrder(Order.asc("año"));
		List<DiaNoHabil> diasNoHabiles = buscarPorCriteria(filtro);
		return diasNoHabiles;
	}

	@Override
	public Busqueda buscarPorDiaNoHabil(DiaNoHabil diaNoHabil, Long paginaActual) {
		Busqueda busqueda = new Busqueda();
		Criterio filtro = Criterio.forClass(DiaNoHabil.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE));
		// filtro.add(Restrictions.ilike("año",diaNoHabil.getAño(),MatchMode.ANYWHERE));
		// filtro.add(Restrictions.eq("fecha",diaNoHabil.getFecha()));
		// filtro.add(Restrictions.ilike("abreviatura",tipoLugar.getAbreviatura(),MatchMode.ANYWHERE));

		Long totalRegistros = diaNoHabilRepositorio.cantidadPorCriteria(filtro);
		if (totalRegistros % Constantes.PAGINACION.USUARIO == 0) {
			busqueda.setNumeroPaginas(totalRegistros
					/ Constantes.PAGINACION.USUARIO);
		} else {
			busqueda.setNumeroPaginas(totalRegistros
					/ Constantes.PAGINACION.USUARIO + 1);
		}
		// validaciones
		if (paginaActual > busqueda.getNumeroPaginas()) {
			busqueda.setPaginaActual(busqueda.getNumeroPaginas());
		} else {
			busqueda.setPaginaActual(paginaActual);
		}
		if (paginaActual <= 0) {
			busqueda.setPaginaActual(1L);
		}
		// /
		// obtenemos los registros de la pagina solicitada
		filtro.setMaxResults(Constantes.PAGINACION.USUARIO.intValue());
		filtro.setFirstResult((busqueda.getPaginaActual().intValue() - 1)
				* Constantes.PAGINACION.USUARIO.intValue());
		filtro.addOrder(Order.asc("año"));
		if(totalRegistros>0){
		busqueda.setRegistos(diaNoHabilRepositorio.buscarPorCriteria(filtro));
		}else{
			busqueda.setRegistos(new ArrayList<>());
		}

		return busqueda;
	}

	@Override
	public void eliminarDiaNoHabil(Long id) {
		DiaNoHabil diaNoHabilActualizar = obtener(id);
		diaNoHabilActualizar.setEstado(Boolean.FALSE);
		diaNoHabilRepositorio.actualizar(diaNoHabilActualizar);

	}

	@Override
	public String getDiaNoHabil(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getCantidadRegistros() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean validarDuplicado(DiaNoHabil diaNoHabil) {
		Criterio filtro = Criterio.forClass(DiaNoHabil.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE));
		filtro.add(Restrictions.eq("fecha", diaNoHabil.getFecha()));
		if (diaNoHabil.getId() != null) {
			filtro.add(Restrictions.not(Restrictions.eq("id",
					diaNoHabil.getId())));
		}
		if (buscarPorCriteria(filtro).size() > 0) {
			return false;
		}
		return true;
	}

	@Override
	public Integer getDiasPlazoVencimiento() {
		return diasPlazoVencimiento;
	}
}
