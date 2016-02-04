package com.geo.services;


	import java.util.HashMap;
	import java.util.List;
	import java.util.Map;
	import java.util.Set;

	import javax.annotation.PostConstruct;
	import javax.persistence.EntityManager;
	import javax.persistence.PersistenceContext;
	import javax.persistence.Query;
	import javax.persistence.criteria.CriteriaQuery;

	import org.jboss.logging.Logger;

	/**
	 * Servicio que permite realizar operaciones en la BDD, insersión,
	 * actualización, borrado, y busquedas por parámetros para cualquier objeto
	 * persistente.
	 * 
	 * @author Clear Minds Consultores Cía. Ltda.
	 * 
	 * @param <T>
	 *            Entidad sobre la cual se realizarán las operaciones.
	 */
	public abstract class ServicioBase<T> {
		
		@PersistenceContext
		protected EntityManager em;

		protected static Logger LOG;
		private Class<T> tipoEntidad;
		private Class<? extends ServicioBase<T>> tipoServicio;
		

		/**
		 * Constructor de la clase Servicio Base
		 * 
		 * @param tipoEntidad
		 *            Entidad sobre la cual se van a realizar las operaciones.
		 * @param tipoServicio
		 *            Servicio desde el cual se va a tratar la entidad, sirve para
		 *            fines de LOG.
		 */
		
		public ServicioBase(Class<T> tipoEntidad,
				Class<? extends ServicioBase<T>> tipoServicio) {
			this.tipoEntidad = tipoEntidad;
			this.tipoServicio = tipoServicio;
		}
		

		@PostConstruct
		private void setLogger() {
			LOG = Logger.getLogger("LogPrueba");
			if (LOG == null) {
				LOG = Logger.getLogger(tipoServicio);
			}
		}

		/**
		 * Permite insertar datos de la entidad.
		 * 
		 * @param entidad
		 *            Objeto que se desea insertar.
		 */
		public void insertar(T entidad) {
			LOG.info("Insertando Entidad>>" + entidad);
			em.persist(entidad);
			em.flush();
		}

		/**
		 * Permite actualizar datos de la entidad.
		 * 
		 * @param entidad
		 *            Objeto que se desea actualizar.
		 */
		public void actualizar(T entidad) {
			LOG.info("Actualizando Entidad>>" + entidad);
			em.merge(entidad);
		}

		/**
		 * Permite actualizar datos de la entidad.
		 * 
		 * @param entidad
		 *            Objeto que se desea eliminar.
		 */
		public void eliminar(T entidad) {
			LOG.info("Eliminando Entidad>>" + entidad);
			em.remove(em.merge(entidad));
		}

		/**
		 * 
		 * @param id
		 * @return
		 */
		public T buscarPorId(Integer id) {
			LOG.info("Buscando Entidad con id>>" + id);
			return em.find(tipoEntidad, id);
		}
		
		
		/**
		 * 
		 * @param id
		 * @return
		 */
		public T buscarPorIdLong(Long id) {
			LOG.info("Buscando Entidad con id long>>" + id);
			return em.find(tipoEntidad, id);
		}

		/**
		 * Busca todos los registros existentes de una entidad.
		 * 
		 * @return Lista de objetos encontrados.
		 */
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public List<T> buscarTodos() {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(tipoEntidad));
			return em.createQuery(cq).getResultList();
		}

		/**
		 * Busca todos los registros, con límite de resultados de acuerdo a los
		 * valores en sus parametros desde y hasta.
		 * 
		 * @param desde
		 *            Desde que registro queremos los datos.
		 * 
		 * @param hasta
		 *            Hasta que registro queremos los datos.
		 * 
		 * @return Lista de objetos con un máximo de resultados.
		 */
		public List<T> buscarTodos(int desde, int hasta) {
			return buscar("SELECT x FROM " + tipoEntidad.getName() + " x",
					new HashMap<String, Object>(), desde, hasta);
		}

		/**
		 * 
		 * Realiza una búsqueda de acuerdo a los parametros recibidos, con límite de
		 * resultados de acuerdo a los valores en sus parametros desde y hasta.
		 * 
		 * @param consultaJPQL
		 *            Consulta que se desea realizar Ejm: SELECT c FROM Cliente c
		 *            WHERE c.id = :id
		 * @param parametros
		 *            Mapa con los parametros y valores Ejm: mapa.put("id", 10)
		 * @param desde
		 *            Desde que registro queremos los datos.
		 * 
		 * @param hasta
		 *            Hasta que registro queremos los datos.
		 * 
		 * @return Lista de objetos con un máximo de resultados que cumple con los
		 *         parámetros de búsqueda.
		 */
		@SuppressWarnings("unchecked")
		public List<T> buscar(String consultaJPQL, Map<String, Object> parametros,
				int desde, int hasta) {
			Set<Map.Entry<String, Object>> rawParametros = parametros.entrySet();
			Query query = this.em.createQuery(consultaJPQL);
			int maximosResultados = hasta - desde;
			if (maximosResultados > 0) {
				query.setMaxResults(maximosResultados);
			}
			LOG.info("Parametros de la consulta " + consultaJPQL + " desde: "
					+ desde + " hasta: " + hasta + ":");
			for (Map.Entry<String, Object> entry : rawParametros) {
				LOG.info(entry.getKey() + " = " + entry.getValue());
				query.setParameter(entry.getKey(), entry.getValue());
			}
			query.setFirstResult(desde);
			System.out.println("max results:" + query.getMaxResults() + " first:"
					+ query.getFirstResult());
			List<T> resultado = query.getResultList();
			LOG.info("RECUPERADOS: " + resultado.size());
			return resultado;
		}

		/**
		 * Realiza una búsqueda de todos los registros, con límite de resultados de
		 * acuerdo a los valores en sus parametros desde y hasta.
		 * 
		 * @param consultaJPQL
		 *            Consulta que se desea realizar Ejm: SELECT c FROM Cliente c
		 * 
		 * @param desde
		 *            Desde que registro queremos los datos.
		 * 
		 * @param hasta
		 *            Hasta que registro queremos los datos.
		 * 
		 * @return Lista de objetos con un máximo de resultados de acuerdo a la
		 *         consulta.
		 */
		public List<T> buscar(String consultaJPQL, int desde, int hasta) {
			return buscar(consultaJPQL, new HashMap<String, Object>(), desde, hasta);
		}

		public List<T> buscarLazy(int desde, int hasta, Map<String, String> filtros) {
			StringBuilder consulta = new StringBuilder("select x from ")
					.append(tipoEntidad.getName()).append(" x");
			Set<Map.Entry<String, String>> clavesMapa = filtros.entrySet();
			int contadorFiltros = 0;
			for (Map.Entry<String, String> entry : clavesMapa) {
				LOG.info(entry.getKey() + " = " + entry.getValue());
				if (contadorFiltros == 0) {
					consulta.append(" WHERE ");
				}
				if (contadorFiltros > 0) {
					consulta.append(" AND ");
				}
				consulta = consulta.append("UPPER("+entry.getKey() + ") LIKE "
						+"'%"+ entry.getValue().toUpperCase()+"%'");
				contadorFiltros++;
			}
			
			LOG.info("CONDICION " + consulta);
			return buscar(consulta.toString(), new HashMap<String, Object>(), desde, hasta);
		}

		/**
		 * Realiza una búsqueda de acuerdo a los parametros recibidos, sin límite de
		 * resultados
		 * 
		 * @param consultaJPQL
		 *            Consulta que se desea realizar Ejm: SELECT c FROM Cliente c
		 *            WHERE c.id = :id
		 * 
		 * @param parametros
		 *            Mapa con los parametros y valores Ejm: mapa.put("id", 10)
		 * 
		 * @return Lista de objetos sin un máximo de resultados que cumple con los
		 *         parámetros de búsqueda.
		 */
		public List<T> buscar(String consultaJPQL, Map<String, Object> parametros) {
			return buscar(consultaJPQL, parametros, 0, 0);
		}

		/**
		 * Retorna el total de registros en la tabla.
		 * 
		 * @return int Numero total de registros en la tabla.
		 */
		public int contarTotalRegistros() {
			Query query = em.createQuery("SELECT COUNT(*) FROM "
					+ tipoEntidad.getName() + " x");
			Number result = (Number) query.getSingleResult();
			LOG.info("Numero total de registros existentes: " + result);
			return result.intValue();
		}
	}