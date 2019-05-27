package com.sige.servicio;

import java.util.List;

import com.sige.entidad.PagosTupa;
import com.sige.entidad.RequisitoTupa;
import com.sige.entidad.TipoTramite;
import com.sige.entidad.Tupa;

public interface TupaService extends BaseServicio<Tupa, Long> {
	public void GuardarTupa(TipoTramite tipoTramite, Tupa tupa,
			List<RequisitoTupa> requisitoTupa, List<PagosTupa> lsPagosTupa);

	public Tupa obtenerTupaPorTramite(String codTramite);

	public void ActualizarTupa(TipoTramite tipoTramite, Tupa tupa,
			List<RequisitoTupa> requisitoTupa, List<PagosTupa> lsPagoTupa);
}
