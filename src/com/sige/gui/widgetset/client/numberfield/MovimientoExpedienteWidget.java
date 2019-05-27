package com.sige.gui.widgetset.client.numberfield;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.vaadin.gwtgraphics.client.DrawingArea;
import org.vaadin.gwtgraphics.client.Line;
import org.vaadin.gwtgraphics.client.shape.Circle;
import org.vaadin.gwtgraphics.client.shape.Text;

import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;

// TODO extend any GWT Widget
public class MovimientoExpedienteWidget extends SimplePanel implements ClickHandler{

	public static final String CLASSNAME = "mycomponent";

	AbsolutePanel p = new AbsolutePanel();
	DrawingArea canvas;
	String texto = "";
	public String[] idTipoDocumento;
	public String[] idDependencias;
	public String[] codigoDocumento;
	public String[] nivel;
	public String[] codigoDocumentoPadre;
	public String[] codigoDocumentoReferencia;
	public String[] nombreDependenciasTotal;
	public String[] idDependenciasTotal;
	public String[] idTipoDocumentos;
	public String[] nombreTipoDocumentos;
	public String idMesaPartes;
	public boolean dibujar = true;
	public List<Punto> puntosL;
	public Short maximoOrigenAnterior=1; 
	public List<String> origenes;
	public PuntoUnion puntoUnion;
	public Map<String,PuntoUnion> puntosUnion;
	int x=0;
	int y=100;
	int ymaximo=50;


	public MovimientoExpedienteWidget() {
		setStyleName(CLASSNAME);
		canvas = new DrawingArea(0,0);
	}

	public void construirGrafico() {

		
		puntosL = new ArrayList<Punto>();
		origenes= new ArrayList<String>();
		puntosUnion= new HashMap<String,PuntoUnion>();
		llenarOrigenes();
		llenarPuntosUnion();
		for(String origen:origenes){
			maximoOrigenAnterior=1;
			Button b= new Button(recortar(getNombreDependencia(idMesaPartes)));
			Button c= new Button(recortar(getNombreDependencia(getIdDependecia(origen))));
			b.setWidth("300px");
			c.setWidth("300px");
			b.setHeight("23px");
			c.setHeight("23px");
			b.setEnabled(false);
			c.setEnabled(false);
			dibujarFlecha(x+200, y+23,x+200,y+100,origen);
			dibujarRamas(origen,x+50,y+100);
			p.add(b,x+50,y);
			p.add(c,x+50,y+100);
			x+=(maximoOrigenAnterior*500);
		}
		canvas.setWidth(x);
		canvas.setHeight(ymaximo+200);	
		Label leyenda= new Label("Dele clic en la flecha para ver el Detalle");
		leyenda.setWidth("120px");
		leyenda.setHeight("80px");
		p.add(leyenda,5,5);
		p.setWidth(x+"px");
		p.setHeight(ymaximo+200+"px");
		p.add(canvas, 0, 0);
		add(p);
	}
	private void dibujarRamas(String codigo,Integer x,Integer yo){
		Map<String,Short> ramas=getRamas(codigo);
		int i=0;
		for(String id:ramas.keySet()){
			i++;
			if(maximoOrigenAnterior<ramas.size()){
				maximoOrigenAnterior=(short) ramas.size();
			}
			if(puntosUnion.get(id)!=null){
				if(!puntosUnion.get(id).getDibujado()){
				Button d= new Button(recortar(getNombreDependencia(getIdDependecia(puntosUnion.get(id).getCodigo()))));
				d.setWidth("300px");
				d.setHeight("23px");
				d.setEnabled(false);
				dibujarFlecha(x+150, yo+23,150+(this.x)+(((puntosUnion.get(id).getCantidadUnion())/2)*170) ,puntosUnion.get(id).getNivelMaximo()*y+100,puntosUnion.get(id).getCodigo());
				p.add(d,this.x+(((puntosUnion.get(id).getCantidadUnion())/2)*170),puntosUnion.get(id).getNivelMaximo()*y+100);
				dibujarRamas(id, this.x+(((puntosUnion.get(id).getCantidadUnion())/2)*170),puntosUnion.get(id).getNivelMaximo()*y+100);
				 puntosUnion.get(id).setDibujado(Boolean.TRUE);
				 puntosUnion.get(id).setX(150+(this.x)+(((puntosUnion.get(id).getCantidadUnion())/2)*170));
				 puntosUnion.get(id).setY(puntosUnion.get(id).getNivelMaximo()*y+100);
				 if(ymaximo<puntosUnion.get(id).getNivelMaximo()*y+100){
						ymaximo=puntosUnion.get(id).getNivelMaximo()*y+100;
					}
				}else{
					dibujarFlecha(150+x, yo+23,puntosUnion.get(id).getX() ,puntosUnion.get(id).getY(),puntosUnion.get(id).getCodigo());
				}
			}else{
				Button b= new Button(recortar(getNombreDependencia(getIdDependecia(id))));
			
				b.setWidth("300px");
				b.setHeight("23px");
				b.setEnabled(false);
				dibujarFlecha(150+x, yo+23, 150+x+((i-1)*170),ramas.get(id)*y+100,id);
				p.add(b,x+((i-1)*170),ramas.get(id)*y+100);
				dibujarRamas(id, x+((i-1)*170),ramas.get(id)*y+100);
			}
			if(ymaximo<ramas.get(id)*y+100){
				ymaximo=ramas.get(id)*y+100;
			}
			
		}
	}
	
	private String recortar(String cadena)
	{
		if(cadena.length()>40){
			return cadena.substring(0,40);
		}else{
			return cadena;
		}
		
	}
	
	private String getIdDependecia(String codigo){
		for(int i=0;i<codigoDocumentoPadre.length;i++){
			if(codigoDocumentoPadre[i].equals(codigo)){
				return idDependencias[i];
			}
		}
		return "";
	}
	private String getNombreDependencia(String Id){
		for(int i=0;i<idDependenciasTotal.length;i++){
			if(idDependenciasTotal[i].equals(Id)){
				return nombreDependenciasTotal[i];
			}
		}
		return "";
	}
	private String getIdTipoDocumento(String codigo){
		for(int i=0;i<codigoDocumentoPadre.length;i++){
			if(codigoDocumentoPadre[i].equals(codigo)){
				return idTipoDocumento[i];
			}
		}
		return "";
	}
	
	private String getNombreTipoDocumento(String id){
		for(int i=0;i<idTipoDocumentos.length;i++){
			if(idTipoDocumentos[i].equals(id)){
				return nombreTipoDocumentos[i];
			}
		}
		return "";
	}
	
	private Map<String,Short> getRamas(String codigo){
		Map<String,Short> respuesta= new HashMap<String, Short>();
		for(int i=0;i<codigoDocumentoReferencia.length;i++){
			if(codigoDocumentoReferencia[i].equals(codigo) && (!codigoDocumento[i].equals(codigo)||!codigoDocumentoPadre[i].equals(codigo))){
				respuesta.put(codigoDocumento[i],Short.parseShort(nivel[i]));
			}
		}
		return respuesta;
	}
	private void llenarPuntosUnion(){
		for(int i =0;i<codigoDocumentoPadre.length;i++){
				isPuntoUnion(codigoDocumentoPadre[i]);
		}
	}
	private void llenarOrigenes(){
		for(int i =0;i<codigoDocumentoPadre.length;i++){
			if(codigoDocumento[i].equals(codigoDocumentoPadre[i])&& codigoDocumento[i].equals(codigoDocumentoReferencia[i])){
				origenes.add(codigoDocumento[i]);
			}
	}
	}
	private void isPuntoUnion(String codigo){
		Short concidencias=0;
		Short maxNivel=0;	
for(int i =0;i<codigoDocumentoPadre.length;i++){
			if(codigoDocumentoPadre[i].equals(codigo)){
				concidencias++;
				if(maxNivel<Short.parseShort(nivel[i])){
					maxNivel=Short.parseShort(nivel[i]);
				}
			}
		}
		if(concidencias>1 && puntosUnion.get(codigo)==null){
			puntoUnion= new PuntoUnion();
			puntoUnion.setCantidadUnion(concidencias);
			puntoUnion.setCodigo(codigo);
			puntoUnion.setNivelMaximo(maxNivel);
			puntoUnion.setDibujado(Boolean.FALSE);
			puntosUnion.put(codigo, puntoUnion);
		}
	}

	public void dibujarFlecha(int x1, int y1, int x2, int y2,String codigo) {
		double ang = 0.0, angSep = 0.0;
		double tx, ty;
		int dist = 0;
		dist = 15;
		ty = -(y1 - y2) * 1.0;
		tx = (x1 - x2) * 1.0;
		ang = Math.atan(ty / tx);

		if (tx < 0) {
			ang += Math.PI;
		}
		angSep = 25.0;
		int x1t, x2t, y1t, y2t;

		x1t = (int) (x2 + dist * Math.cos(ang - Math.toRadians(angSep)));
		y1t = (int) (y2 - dist * Math.sin(ang - Math.toRadians(angSep)));
		x2t = (int) (x2 + dist * Math.cos(ang + Math.toRadians(angSep)));
		y2t = (int) (y2 - dist * Math.sin(ang + Math.toRadians(angSep)));

		Linea linea = new Linea(x1, y1, x2, y2);
		linea.setStrokeColor("orange");
		linea.setStrokeOpacity(0.7);
		nombretipoDoc=getNombreTipoDocumento(getIdTipoDocumento(codigo));
		linea.setStrokeWidth(3);
		linea.setTitle(nombretipoDoc);

		Linea l = new Linea(x1t, y1t, x2, y2);

		Linea ln = new Linea(x2t, y2t, x2, y2);
		
		l.setStrokeColor("orange");
		l.setStrokeOpacity(0.7);
		ln.setStrokeColor("orange");
		ln.setStrokeOpacity(0.7);
		l.setStrokeWidth(3);
		l.setTitle(nombretipoDoc);
		ln.setStrokeWidth(3);
		ln.setTitle(nombretipoDoc);
		l.addClickHandler((ClickHandler) this);
		ln.addClickHandler((ClickHandler) this);
		linea.addClickHandler((ClickHandler) this);
		canvas.add(linea);
		canvas.add(l);
		canvas.add(ln);
		linea.setCodigo(codigo);
		l.setCodigo(codigo);
		ln.setCodigo(codigo);
		
	}
	String nombretipoDoc;


	@Override
	public void onClick(ClickEvent event) {
		if(event.getSource() instanceof Linea){
			Linea l= (Linea) event.getSource();
			setCodigo(l.getCodigo());
		}
	}

	String codigo="";
	public String getCodigo(){
		return codigo;
	}
	public void setCodigo(String codigo){
		this.codigo=codigo;
	}
}
class Linea extends Line{
	
	private String codigo;
	
	public Linea(int x1, int y1, int x2, int y2) {
		super(x1, y1, x2, y2);
		// TODO Auto-generated constructor stub
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
}


class Punto {
	private Integer x;
	private Integer y;

	public Punto(Integer x, Integer y) {
		this.x = x;
		this.y = y;
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	@Override
	public boolean equals(Object o) {
		boolean respuesta = false;
		if (o instanceof Punto) {
			Punto punto = (Punto) o;
			if (this.x.equals(punto.x) && this.y.equals(punto.y)) {
				respuesta = true;
			}
		}
		return respuesta;
	}
}

class PuntoUnion{
			private String codigo;
			private Short cantidadUnion;
			private Short nivelMaximo;
			private Boolean dibujado;
			private Integer x;
			private Integer y;
			public String getCodigo() {
				return codigo;
			}
			public void setCodigo(String codigo) {
				this.codigo = codigo;
			}
			public Short getCantidadUnion() {
				return cantidadUnion;
			}
			public void setCantidadUnion(Short cantidadUnion) {
				this.cantidadUnion = cantidadUnion;
			}
			public Short getNivelMaximo() {
				return nivelMaximo;
			}
			public void setNivelMaximo(Short nivelMaximo) {
				this.nivelMaximo = nivelMaximo;
			}
			public Boolean getDibujado() {
				return dibujado;
			}
			public void setDibujado(Boolean dibujado) {
				this.dibujado = dibujado;
			}
			public Integer getX() {
				return x;
			}
			public void setX(Integer x) {
				this.x = x;
			}
			public Integer getY() {
				return y;
			}
			public void setY(Integer y) {
				this.y = y;
			}
			
			
	}
