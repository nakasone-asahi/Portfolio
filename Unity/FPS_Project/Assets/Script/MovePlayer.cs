using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class MovePlayer : MonoBehaviour
{
    // Start is called before the first frame update
    public float speed = 5.0f;
    [SerializeField] Transform tform;

    void Start()
    {
    }

    // Update is called once per frame
    void Update()
    {
        Vector3 cameraForward = Vector3.Scale(Camera.main.transform.forward, new Vector3(1, 0, 1)).normalized;
         // キャラクターの向きを進行方向に
        if (cameraForward != Vector3.zero)
        {
            transform.rotation = Quaternion.LookRotation(cameraForward);
        }
        if (Input.GetKey(KeyCode.W))
        {
             cameraForward = Vector3.Scale(Camera.main.transform.forward, new Vector3(1, 0, 1)).normalized;
            transform.position += cameraForward * speed * Time.deltaTime;
        }
        if (Input.GetKey(KeyCode.S))
        {
             cameraForward = Vector3.Scale(Camera.main.transform.forward, new Vector3(1, 0, 1)).normalized;
            transform.position -= cameraForward * speed * Time.deltaTime;
        }
        if (Input.GetKey(KeyCode.A))
        {
             cameraForward = Vector3.Scale(Camera.main.transform.right, new Vector3(1, 0, 1)).normalized;
            transform.position -= cameraForward * speed * Time.deltaTime;
        }
        if (Input.GetKey(KeyCode.D))
        {
             cameraForward = Vector3.Scale(Camera.main.transform.right, new Vector3(1, 0, 1)).normalized;
            transform.position += cameraForward * speed * Time.deltaTime;
        }
       
    }

    void OnCollisionEnter(Collision collision)
    {
        if (collision.gameObject.name == "Enemy")
        {
            UnityEditor.EditorApplication.isPlaying = false;
        }
    }

}
