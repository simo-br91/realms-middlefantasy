from PIL import Image

def process_image(input_path, output_path, size=(32, 32)):
    # Load the image
    img = Image.open(input_path)

    # Resize the image to the desired size (32x32)
    img_resized = img.resize(size, Image.NEAREST)  # NEAREST for pixel art

    # Convert to RGBA to ensure transparency
    if img_resized.mode != 'RGBA':
        img_resized = img_resized.convert('RGBA')

    # Save the processed image with transparency
    img_resized.save(output_path, format='PNG')
    print(f"Image saved to {output_path}")

# Example usage
input_image_path = 'C:/Users/simor/Documents/realms-middlefantasy/src/main/resources/assets/realms_middlefantasy/textures/block/black_iron_block.png'  # Replace with your image path
output_image_path = 'C:/Users/simor/Documents/realms-middlefantasy/black_iron_block.png'  # Replace with the desired output path

process_image(input_image_path, output_image_path)
