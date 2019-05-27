package com.sige.servicio;

import com.sige.entidad.Dependencia;
import com.sige.entidad.DependenciaTipoTramite;
import com.sige.entidad.DependenciaTipoTramitePlantilla;
import com.sige.entidad.TipoTramite;
import com.sige.util.Busqueda;

public interface DependenciaTipoTramiteService extends
		BaseServicio<DependenciaTipoTramite, Long> {

	public DependenciaTipoTramite getDependenciaTipoTramite(
			Dependencia dependencia, TipoTramite tipoTramite);

}
