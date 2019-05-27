package com.sige.servicio;

import java.util.List;

import com.sige.entidad.TipoTramite;
import com.sige.util.Busqueda;
import com.sige.util.TipoTramiteCanonico;

public interface TipoTramiteService extends BaseServicio<TipoTramite, Long> {
	public List<TipoTramite> BuscarPorTipoTramite(TipoTramite tipoTramite);

	public Busqueda BuscarPorTipoTramite(TipoTramite tipoTramite,
			Long paginaActual, TipoTramiteCanonico canonico);

	public List<TipoTramite> obtenerUltimoRegistro();

	public TipoTramite obtenerTipoTramitePorCod(String codTramite);

	public List<TipoTramite> obtenerTipoTramiteNoTupa();

	public List<TipoTramite> obtenerTipoTramiteTupa();
	// public void eliminarTipoTramite(TipoTramite tipoTramite, )
}
