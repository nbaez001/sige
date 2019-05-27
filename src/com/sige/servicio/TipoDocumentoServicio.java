package com.sige.servicio;

import java.util.List;

import com.sige.entidad.TipoDocumento;
import com.sige.util.Busqueda;

public interface TipoDocumentoServicio extends
		BaseServicio<TipoDocumento, Long> {

	public List<TipoDocumento> buscarPorTipoDocumento(TipoDocumento documento);

	public Busqueda buscarPorTipoDocumento(TipoDocumento documento,
			Long paginaActual);

	public void eliminarTipoDocumento(TipoDocumento tipoDocumento);

	public String getTipoDocumento(Long id);

	public Long getCantidadRegistros();

	public Boolean validarDuplicado(TipoDocumento tipoDocumento);

	public TipoDocumento obtenerTipoDocumento(String codTipoDocumento);

	public List<TipoDocumento> obtenerDocumentosExpediente();

}